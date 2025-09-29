package com.dilshad.baselib.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("data")
    var data: Data,
    @SerializedName("id")
    var id: String,
    @SerializedName("message")
    var message: String
)
