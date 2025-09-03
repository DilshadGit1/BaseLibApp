package com.example.municipal.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.MediaDrm
import android.media.UnsupportedSchemeException
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Base64
import android.util.Log
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.UpdateAvailability
import java.util.*

object CommonUtil {

    fun getLastBitFromUrl(url: String): String? {
        // return url.replaceFirst("[^?]*/(.*?)(?:\\?.*)","$1);" <-- incorrect
        return url.replaceFirst(".*/([^/?]+).*".toRegex(), "$1")
    }


    @SuppressLint("MissingPermission", "HardwareIds")
    fun getIMEI_OrDeviceId(context: Context): String? {
        val deviceId: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        } else {
            val mTelephony = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (context.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                    return "";
//                }
                val WIDEVINE_UUID = UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L)
                var drumIdByteArray: ByteArray? = null
                try {
                    drumIdByteArray = MediaDrm(WIDEVINE_UUID)
                        .getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
                    return Base64.encodeToString(drumIdByteArray, Base64.DEFAULT)
                } catch (e: UnsupportedSchemeException) {
                    e.printStackTrace()
                }
                return ""
            }
            assert(mTelephony != null)
            deviceId = if (mTelephony.deviceId != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mTelephony.imei
                } else {
                    mTelephony.deviceId
                }
            } else {
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            }
        }
        Log.d("deviceId", deviceId)
        return deviceId
    }

}