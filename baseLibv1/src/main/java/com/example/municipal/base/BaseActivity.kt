package com.example.municipal.base

import android.Manifest
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.example.municipal.ActivityViewModel
import com.example.municipal.R
import com.example.municipal.common.LoadingDialog
import com.example.municipal.model.User
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
        sp=getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        loadSpData()
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(this@BaseActivity, layoutId) as T
        binding.lifecycleOwner = this

        loadingDialog= LoadingDialog.initLoadingDialog(this)
//        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        addObservers()
        setUpClicks()

        onInitialized()

    }
    private fun loadSpData(){
        login_userName=sp.getString("login_userName","")?:""
        login_userType=sp.getString("login_userType","")?:""
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

    override fun onDestroy() {
        super.onDestroy()

    }


}