package com.dilshad.baselib.model

data class SimpleModel(
    val name: String,
    val id: String,
    val title: String,
    val uniqueId: String

){
    var rate: Double = 0.0
    var doe: String? =null
    var desc: String?=""
    var profile: String?=""
}