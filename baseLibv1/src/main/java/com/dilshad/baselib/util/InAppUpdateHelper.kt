package com.dilshad.baselib.util

import android.app.Activity
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.*
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.install.model.InstallStatus

class InAppUpdateHelper private constructor(
    private val activity: ComponentActivity,
    private val rootView: View
) : DefaultLifecycleObserver {

    private val appUpdateManager = AppUpdateManagerFactory.create(activity)

    private val updateLauncher: ActivityResultLauncher<IntentSenderRequest> =
        activity.registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                // user canceled or update failed
            }
        }

    init {
        activity.lifecycle.addObserver(this)
        checkForUpdate()
    }

    private fun checkForUpdate() {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                val updateType =
                    if (info.updatePriority() == 0) AppUpdateType.IMMEDIATE
                    else AppUpdateType.FLEXIBLE

                val options = AppUpdateOptions.newBuilder(updateType)
                    .setAllowAssetPackDeletion(true)
                    .build()

                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateLauncher,
                    options
                )
            }
        }

        // Flexible update → show Snackbar once downloaded
        appUpdateManager.registerListener { state ->
            if (state.installStatus() == InstallStatus.DOWNLOADED) {
                Snackbar.make(rootView, "Update ready to install", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Restart") {
                        appUpdateManager.completeUpdate()
                    }.show()
            }
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        appUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            if (info.updateAvailability() ==
                UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
            ) {
                val options = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
                appUpdateManager.startUpdateFlowForResult(
                    info,
                    updateLauncher,
                    options
                )
            }
        }
    }

    companion object {
        fun attach(activity: ComponentActivity): InAppUpdateHelper {
            return InAppUpdateHelper(activity, activity.findViewById(android.R.id.content))
        }
    }
}
