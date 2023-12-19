package com.example.municipal.common

import android.graphics.Color
import android.widget.EditText
import android.widget.Toast


fun EditText.isNameValid():Boolean{
        return this.text.isNotEmpty() && this.text.length>2
    }

@Throws(Exception::class)
fun EditText.isInputValidWithThrows():EditText  {
    if(this.text.isNotEmpty() && this.text.length>2 ){
        return this
    }else{
        var txt="Input valid data"

        if(this.hint.toString().isNotEmpty()){
            txt=this.hint.toString()
        }else{
            this.hint = "Please enter valid input here"
        }
        Toast.makeText(this.context,txt,Toast.LENGTH_SHORT).show()
        this.setHintTextColor(Color.RED)

        throw Exception("Some input empty/invalid")
        return this
    }
}
@Throws(Exception::class)
fun EditText.isValidMobileNoWithThrows():EditText  {
    if(this.text.isNotEmpty() && this.text.length==10 ){
        return this
    }else{
        var txt="Input valid mobile no"

        if(this.hint.toString().isNotEmpty()){
            txt=this.hint.toString()
        }else{
            this.hint = "Please enter valid mobile No "
        }
        Toast.makeText(this.context,txt,Toast.LENGTH_SHORT).show()
        this.setHintTextColor(Color.RED)

        throw Exception("Enter a valid Mobile no")
        return this
    }
}
@Throws(Exception::class)
fun EditText.checkInputIsEmptyWithThrows():EditText  {
    if(this.text.isNotEmpty() ){
        return this
    }else{
        var txt="Input valid data"

        if(this.hint.toString().isNotEmpty()){
            txt=this.hint.toString()
        }else{
            this.hint = "Please enter valid input here"
        }
        Toast.makeText(this.context,txt,Toast.LENGTH_SHORT).show()
        this.setHintTextColor(Color.RED)

        throw Exception("Some input empty/invalid")
        return this
    }
}
fun EditText.isIntValueValid():Boolean{
    return try {
        this.text.isNotEmpty() && Integer.valueOf(this.text.toString())>0
    }catch (ex:Exception){
        false
    }


}