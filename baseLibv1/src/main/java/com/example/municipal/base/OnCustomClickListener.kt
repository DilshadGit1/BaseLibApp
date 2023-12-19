package com.example.municipal.base

import android.view.View

interface OnCustomClickListener {
    fun onClickItem(view: View?,obj:Any?,code: Int,msg:String)
}