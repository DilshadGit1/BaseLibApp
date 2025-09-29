package com.dilshad.baselib.base

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import cn.pedant.SweetAlert.SweetAlertDialog
import com.dilshad.android.baselib.R
import com.dilshad.baselib.common.LoadingDialog



abstract class BaseDialogFragment<T : ViewDataBinding>( val layoutId: Int) :
    DialogFragment(), BaseControlInterface {


    lateinit var binding: T

//    abstract fun getViewBinding(): T

    lateinit var  loadingDialog: AlertDialog

    val baseViewModel: BaseViewModel by viewModels()

    lateinit var sp: SharedPreferences

    var isDialogOn=false

    lateinit var loginUserId:String
    lateinit var loginUserType:String
    lateinit var baseURL:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme);
        sp=requireContext().getSharedPreferences(getString(R.string.app_name),
            AppCompatActivity.MODE_PRIVATE
        )
        loadDataFromSP()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = getViewBinding()
//        binding.la = this
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        loadingDialog= LoadingDialog.initLoadingDialog(requireContext())
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)

        (binding as ViewDataBinding).lifecycleOwner= this
           return (binding as ViewDataBinding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        setUpClicks()
        onInitialized()
    }

    private fun loadDataFromSP(){
//        val sp=requireContext().getSharedPreferences("",Context.MODE_PRIVATE)
        loginUserId=sp.getString("login_userName","")?:""
        loginUserType=sp.getString("login_userType","")?:""
        baseURL=sp.getString("BaseUrl","")?:""

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
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showWarningDialog(msg: String,id:Int,showCancelBtn:Boolean,onDialogInterface: DialogInterface.OnClickListener?){
        customDialog(SweetAlertDialog.WARNING_TYPE,"Alert",msg,id,showCancelBtn,onDialogInterface)
    }
    fun showSuccessDialog(msg: String,id:Int,showCancelBtn:Boolean,onDialogInterface: DialogInterface.OnClickListener){
        customDialog(SweetAlertDialog.SUCCESS_TYPE,"Success",msg,id,showCancelBtn,onDialogInterface)
    }
    fun showErrorDialog(msg: String,id:Int,showCancelBtn:Boolean,onDialogInterface: DialogInterface.OnClickListener?){
        customDialog(SweetAlertDialog.ERROR_TYPE,"Error",msg,id,showCancelBtn,onDialogInterface)
    }

    fun showDialog( msg: String,id:Int,showCancelBtn:Boolean,onDialogInterface: DialogInterface.OnClickListener?){
        customDialog(SweetAlertDialog.NORMAL_TYPE,"Alert",msg,id,showCancelBtn,onDialogInterface)
    }
    fun showSimpleDialog( msg: String){
        customDialog(SweetAlertDialog.NORMAL_TYPE,"Alert",msg,-1,false,null)
    }

    private fun customDialog(alertType:Int,title:String, msg: String,id:Int,showCancelBtn:Boolean,onDialogInterface: DialogInterface.OnClickListener?){
        val dialog= SweetAlertDialog(requireContext(), alertType)
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
}