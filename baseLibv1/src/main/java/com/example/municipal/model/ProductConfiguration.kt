package com.example.municipal.model

data class ProductConfiguration(
    val name: String,
    val lang: String,
    val logo: String,
    val waterMark: String,
    val logoWithName: String,
    val email: Email,
    val mobile: String,
    val phone: String,
    val address: String,
    val copyrights: String,
    val poweredBy: String,
    val rights: String,
    val doaStartYear: String,
    val isLegacy: String,
    val isLegacyV1: String,
    val isLegacyV2: String,
    val isCgBhilai: String,
    val isMobileTower: String,
    val isDefault: Boolean,
    val isCaptcha: String,
    val fixedArvtaxCalc: String,
    val propertyHoldPayment: String,
    val locationId: String,
    val fySelection: String,
    val status: String,
    val uniqueId: String,
    val tlPart2: String,
    val created: Created,
    val location: Location,
    val reportMgmt: String,
    val waterBillMgmt: String,
    val tradeLisenceMgmt: String,
    val tlRateMaster: String,
    val shopRentMgmt: String,
    val accountMgmt: String,
    val operationsMgmt: String,
    val oldDemandReceipt: String,
    val payWhilePropertyStatusPending: String,
    val appVersion: Long,
    val municipality: SimpleModel,
    val state: SimpleModel,
    val district: SimpleModel,
    val village: SimpleModel,
    val yoa: SimpleModel,
    val showPaytmButton: String,
    val showHdfcButton: String,
    val ref: Map<String, Any>,
){
    var userChargeNewAssessmentV1:String=""
    var userChargeNewAssessmentV2:String=""
    var propertyMgmt:String=""
    var userChargeMgmt:String=""
}
data class Email(
    val contactUs: String,
    val sendEmail: String,
)

data class Created(
    val on: Any?,
    val scenario: String,
    val by: String
)

