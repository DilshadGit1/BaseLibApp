package com.dilshad.baselib.base

import android.view.View

interface OnCustomClickListener {
    fun onClickItem(view: View?,obj:Any?,code: Int,msg:String)
}