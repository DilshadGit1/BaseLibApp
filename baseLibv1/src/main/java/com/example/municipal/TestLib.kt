package com.example.municipal

import android.content.Context
import android.widget.Toast

class TestLib {

    fun print2(context: Context){
        Toast.makeText(context,"Test lib "+this.javaClass.name,Toast.LENGTH_SHORT).show()
    }
    fun printPkgName(context: Context){
        Toast.makeText(context,"Test lib "+this.javaClass.`package`,Toast.LENGTH_SHORT).show()
    }

    fun print(context: Context){
        Toast.makeText(context,"Test lib ",Toast.LENGTH_SHORT).show()
    }
}