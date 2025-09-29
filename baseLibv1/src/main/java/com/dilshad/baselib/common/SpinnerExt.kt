package com.dilshad.baselib.common

import android.widget.Spinner
import android.widget.Toast

@Throws(Exception::class)
fun Spinner.isZeroIndex():Spinner{
    if(this.selectedItemPosition==0){
        Toast.makeText(this.context,""+selectedItem.toString(), Toast.LENGTH_SHORT).show()
        throw Exception()
    }else{
        return this
    }
}

@Throws(Exception::class)
fun CustomSpinner.isZeroIndex():CustomSpinner{
    if(this.selectedItemPosition==0){
        Toast.makeText(this.context,""+selectedItem.toString(), Toast.LENGTH_SHORT).show()
        throw Exception()
    }else{
        return this
    }
}