package com.dilshad.baselib.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(@SerializedName("userName")
                        var email: String,
                        @SerializedName("password")
                        var password: String)
