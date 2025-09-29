package com.dilshad.baselib.model

import com.google.gson.JsonElement

data class User(
    val id: String,
    val userName: String,
    val uniqueId: String,
    val mobile: String,
    val type: String,
    val name: String,
    var profile:String="",
    var fatherRpanRhusband:String="",
    var gender:String="",
    var email:String="",
    var dob:String="",

)
{
    var departmentIndex:Int=0
     var roleIndex:Int=0
     var genderIndex:Int=0



     val access: AccessModel? = null
     val accessPrivilege: JsonElement? = null

}


