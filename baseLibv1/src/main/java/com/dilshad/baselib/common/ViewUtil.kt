package com.dilshad.baselib.common

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnCancelListener
import android.net.Uri
import android.os.Build
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.dilshad.android.baselib.R

import com.dilshad.baselib.base.OnCustomClickListener
import com.dilshad.baselib.util.DateTimeUtil

object ViewUtil {
        fun setEmptyTextView(root: ViewGroup){
           val tv= TextView(root.context)
            tv.text="No data found"
            tv.gravity=Gravity.CENTER
            tv.layoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
            root.removeAllViews()
            root.addView(tv)
        }
    fun setEmptyTextViewTwo(root: ViewGroup){
           val tv= TextView(root.context)
            tv.text="No data found"
            tv.gravity=Gravity.CENTER
            tv.layoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
//            root.removeAllViews()
            root.addView(tv)
        }
    fun setFormattedDateInTextView(textView: TextView,date:String){
        val fdate=DateTimeUtil.getDateOnly(date)
        textView.text=fdate
        textView.setTag(android.R.string.ok,date)
    }

    fun deleteItem(context:Context,onClickListener: DialogInterface.OnClickListener): Dialog {
        val dialog= AlertDialog.Builder(context).setMessage("Do You Want to Delete").
        setPositiveButton("OK"
        ) { dialog, which -> onClickListener.onClick(dialog, which) }.
        setNegativeButton("Cancel"
        ) { dialog, which -> dialog?.dismiss() }.create();
        dialog.show()
        return dialog
    }
    fun getGenderList():List<List<String>>{
        val m= mutableListOf<List<String>>()
        m.add(listOf("Male","male"))
        m.add(listOf("Female","female"))
        return m
    }
    fun makeGenderSpinner(spinner: Spinner){
        val adaptor=CustomSpinnerAdaptor(spinner.context, getGenderList())
        spinner.adapter=adaptor
    }
    fun getModeList():List<List<String>>{
        val m= mutableListOf<List<String>>()
        m.add(listOf("DD","dd"))
        m.add(listOf("Cheque","cheque"))
        m.add(listOf("Online","online"))
        return m
    }
    fun makeModeSpinner(spinner: Spinner){
        val adaptor=CustomSpinnerAdaptor(spinner.context, getModeList())
        spinner.adapter=adaptor
    }
    fun getDepartmentList():List<List<String>>{
        val m= mutableListOf<List<String>>()
        m.add(listOf("Municipal","municipal"))
        m.add(listOf("Agriculture","agriculture"))
        return m
    }
    fun makeDepartmentSpinner(spinner: Spinner){
        val adaptor=CustomSpinnerAdaptor(spinner.context, getDepartmentList())
        spinner.adapter=adaptor
    }


    fun showImageInDialog(context: Context?, uri: Uri, onCancelListener: OnCancelListener?) {
        val imageView1 = ImageView(context)
        imageView1.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200)
        val dialog = Dialog(context!!, R.style.AppTheme_Dialog_Custom)
//        val progressBar = ProgressBar(context)
        dialog.setContentView(imageView1)

        imageView1.setImageURI(uri)
        if(onCancelListener!=null){
            dialog.setOnCancelListener(onCancelListener)
        }
        dialog.show()
    }
    fun showDialogWithEditText(context: Context,title:String,onCustomClickListener: OnCustomClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        val input = EditText(context)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton(
            "OK"
            ,null)
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }

        builder.create().apply {
            setOnShowListener {
                getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    //Validate and dismiss
                    if(input.text.toString().isEmpty()){
                        input.hint="Please enter comment here"
                    }else{
                        onCustomClickListener.onClickItem(null,input.text.toString(),0,input.text.toString())
                        dismiss()
                    }
                }
            }
        }.show()
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setBackgroundTintColor(view: View, color:Int){
        val colorResource = ContextCompat.getColorStateList(view.context, color)
        view.backgroundTintList = colorResource
    }
}