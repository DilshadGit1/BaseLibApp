package com.dilshad.appInfoSdk


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File
import java.security.MessageDigest
import java.util.Locale
import java.util.UUID
import java.util.concurrent.atomic.AtomicBoolean

object AppInfoSDK {

    private const val PREF_NAME = "app_info_sdk"

    private lateinit var appContext: Context
    private val isInitialized = AtomicBoolean(false)

    // In-memory cache (FASTEST)
    private var staticCache: StaticInfo? = null

    private val gson by lazy { Gson() }

    /* =======================
       INIT (Application)
       ======================= */

    fun init(context: Context) {
        if (isInitialized.compareAndSet(false, true)) {
            appContext = context.applicationContext
            preloadStaticInfo()
        }
    }

    /* =======================
       PUBLIC API
       ======================= */

    fun collect(): AppInfoPayload {
        check(::appContext.isInitialized) {
            "AppInfoSDK not initialized. Call AppInfoSDK.init(applicationContext)"
        }

        val static = staticCache ?: preloadStaticInfo()
        val dynamic = getDynamicInfo()

        return AppInfoPayload(
            deviceId = static.deviceId,
            androidIdHash = static.androidIdHash,
            model = static.model,
            manufacturer = static.manufacturer,
            osVersion = static.osVersion,
            appVersion = static.appVersion,
            buildNumber = static.buildNumber,
            isRooted = static.isRooted,
            isEmulator = static.isEmulator,
            networkType = dynamic.networkType,
            carrier = dynamic.carrier,
            country = dynamic.country
        )
    }

    /* =======================
       STATIC (ONCE ONLY)
       ======================= */

    private fun preloadStaticInfo(): StaticInfo {
        staticCache?.let { return it }

        val prefs = appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        val deviceId = prefs.getString("device_id", null)
            ?: UUID.randomUUID().toString().also {
                prefs.edit().putString("device_id", it).apply()
            }

        val androidIdHash = prefs.getString("android_id_hash", null)
            ?: sha256(
                Settings.Secure.getString(
                    appContext.contentResolver,
                    Settings.Secure.ANDROID_ID
                ) ?: "unknown"
            ).also {
                prefs.edit().putString("android_id_hash", it).apply()
            }

        val info = appContext.packageManager.getPackageInfo(appContext.packageName, 0)

        val appVersion = info.versionName ?: "unknown"
        val buildNumber =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
                info.longVersionCode.toInt()
            else {
                @Suppress("DEPRECATION")
                info.versionCode
            }

        val static = StaticInfo(
            deviceId = deviceId,
            androidIdHash = androidIdHash,
            model = Build.MODEL,
            manufacturer = Build.MANUFACTURER,
            osVersion = "Android ${Build.VERSION.RELEASE}",
            appVersion = appVersion,
            buildNumber = buildNumber,
            isRooted = isRooted(),
            isEmulator = isEmulator()
        )

        staticCache = static
        return static
    }

    /* =======================
       DYNAMIC (LIGHTWEIGHT)
       ======================= */

    private fun getDynamicInfo(): DynamicInfo {
        return DynamicInfo(
            networkType = getNetworkType(),
            carrier = getCarrier(),
            country = Locale.getDefault().country
        )
    }

    /* =======================
       HELPERS
       ======================= */

    private fun sha256(input: String): String =
        MessageDigest.getInstance("SHA-256")
            .digest(input.toByteArray())
            .joinToString("") { "%02x".format(it) }

    private fun isRooted(): Boolean =
        listOf(
            "/system/bin/su",
            "/system/xbin/su",
            "/sbin/su"
        ).any { File(it).exists() }

    private fun isEmulator(): Boolean =
        Build.FINGERPRINT.contains("generic") ||
                Build.MODEL.contains("Emulator") ||
                Build.BRAND.startsWith("generic")

    private fun getNetworkType(): String {
        val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = cm.activeNetwork ?: return "UNKNOWN"
            val caps = cm.getNetworkCapabilities(network) ?: return "UNKNOWN"

            when {
                caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> "WIFI"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> "MOBILE"
                caps.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> "ETHERNET"
                else -> "UNKNOWN"
            }
        } else {
            @Suppress("DEPRECATION")
            when (cm.activeNetworkInfo?.type) {
                ConnectivityManager.TYPE_WIFI -> "WIFI"
                ConnectivityManager.TYPE_MOBILE -> "MOBILE"
                else -> "UNKNOWN"
            }
        }
    }

    private fun getCarrier(): String? {
        val tm = appContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.networkOperatorName.takeIf { it.isNotBlank() }
    }

    /* =======================
       INTERNAL MODELS
       ======================= */

    private data class StaticInfo(
        val deviceId: String,
        val androidIdHash: String,
        val model: String,
        val manufacturer: String,
        val osVersion: String,
        val appVersion: String,
        val buildNumber: Int,
        val isRooted: Boolean,
        val isEmulator: Boolean
    )

    private data class DynamicInfo(
        val networkType: String,
        val carrier: String?,
        val country: String
    )

    /**
     * Returns AppInfoPayload as Gson JsonObject
     */
    fun collectAsJsonObject(): JsonObject {
        val payload = collect()
        return gson.toJsonTree(payload).asJsonObject
    }
}

/* =======================
   PUBLIC PAYLOAD
   ======================= */

data class AppInfoPayload(
    val deviceId: String,
    val androidIdHash: String,
    val model: String,
    val manufacturer: String,
    val osVersion: String,
    val appVersion: String,
    val buildNumber: Int,
    val isRooted: Boolean,
    val isEmulator: Boolean,
    val networkType: String,
    val carrier: String?,
    val country: String
)
