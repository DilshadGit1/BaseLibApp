package com.dilshad.baselib.model

data class AccessModel(
     val ModuleAccess: ArrayList<ModuleaccessModel?>,
     val FeatureAccess: ArrayList<ModuleaccessModel?>? = null,
     val MenuAccess: ArrayList<ModuleaccessModel?>? = null,
     val TabAccess: ArrayList<ModuleaccessModel?>? = null
)
data class ModuleaccessModel(
    val uniqueId: String,
    val gender: String,
    val mobile: String,
    val email: String,
    val fatherRpanRhusband: String,
    val access: ModuleaccessModel?,
    val check: String,
    val desc: String,
    val name: String,
    val status: String,
    val dateTo: String,
    val dateFrom: String,

    )