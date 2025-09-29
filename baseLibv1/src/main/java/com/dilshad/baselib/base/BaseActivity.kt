package com.dilshad.baselib.base

import android.Manifest
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dilshad.android.baselib.R
import com.dilshad.backupdashboard.MainActivity
import com.dilshad.baselib.common.LoadingDialog
import com.dilshad.baselib.util.InAppUpdateHelper
import com.dilshad.baselib.util.PrefManager

import com.permissionx.guolindev.PermissionX


abstract class BaseActivity<T>(@LayoutRes val layoutId: Int)  :
    AppCompatActivity(), BaseControlInterface where T : ViewBinding ,T :ViewDataBinding {


    /**
     * activity layout view binding object
     */
    lateinit var binding: T


//    private lateinit var mFusedLocationClient:FusedLocationProviderClient
    private lateinit var  loadingDialog: AlertDialog
    lateinit var sp:SharedPreferences
    lateinit var login_userName:String
    lateinit var login_userType:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        sp= PrefManager.getPrefs(this@BaseActivity)

        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutId) as T
        binding.lifecycleOwner = this
        InAppUpdateHelper.attach(this)
        loadingDialog= LoadingDialog.initLoadingDialog(this)
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        addObservers()
        setUpClicks()

        onInitialized()

    }

    private fun checkPermission(){
        PermissionX.init(this)
            .permissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(deniedList, "Core fundamental are based on these permissions", "OK", "Cancel")
            }
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
//                    showToast("All permissions are granted")

                } else {
                    showToast( "These permissions are denied:")
                }
            }
    }


    fun showLoading() {
        loadingDialog.show()
    }

    fun stopLoading() {
        loadingDialog.dismiss()
    }
    fun processError(msg: String?) {
        showToast("Error:$msg")
    }

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    fun showSimpleDialog( msg: String){
        customDialog2(SweetAlertDialog.NORMAL_TYPE,"Alert",msg,-1,false,null)
    }
    fun customDialog(alertType:Int,title:String, msg: String,id:Int,showCancelBtn:Boolean,onDialogInterface: DialogInterface.OnClickListener?){
        val dialog= SweetAlertDialog(this, alertType)
            .setTitleText(title)
            .setContentText(msg)
            .setConfirmText("OK")
            .setConfirmClickListener {
                    sDialog -> sDialog.dismissWithAnimation()
                if(onDialogInterface!=null) {
                    onDialogInterface.onClick(sDialog, id)
                }
            }
        if(showCancelBtn){
            dialog.cancelText = "Cancel"
            dialog.setCancelClickListener(){
                    sDialog -> sDialog.dismissWithAnimation()
            }
        }
        dialog.setCancelable(false)
        dialog.show()
    }

    private fun customDialog2(alertType:Int,title:String, msg: String,id:Int,showCancelBtn:Boolean,onDialogInterface: DialogInterface.OnClickListener?){
        val dialog= SweetAlertDialog(this, alertType)
            .setTitleText(title)
            .setContentText(msg)
            .setConfirmText("OK")
            .setConfirmClickListener {
                    sDialog -> sDialog.dismissWithAnimation()
                if(onDialogInterface!=null) {
                    onDialogInterface.onClick(sDialog, id)
                }
            }
        if(showCancelBtn){
            dialog.cancelText = "Cancel"
            dialog.setCancelClickListener(){
                    sDialog -> sDialog.dismissWithAnimation()
            }
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

//    abstract fun GridLayoutManager(activity: MainActivity, i: Int)


}