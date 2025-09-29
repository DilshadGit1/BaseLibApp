package com.dilshad.baselib.common

import android.content.Context
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog

class LoadingDialog {

    companion object{
        fun initLoadingDialog(context: Context): AlertDialog {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setCancelable(false) // if you want user to wait for some process to finish,
            val p = ProgressBar(context)
            builder.setView(p)
            return builder.create()
        }
    }

}