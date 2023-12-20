package com.example.municipal.model

import com.google.gson.JsonElement

data class RootResponse(val response: Response)

data class Response (
    val statusCode: Long,
    val message: String,
    val data: Data
)
data class Data (
    val data: JsonElement,
    val access: AccessModel,
    val uri: JsonElement,
    val FileURIs: JsonElement,
    val user: JsonElement,
    val userchargecategory: JsonElement,
    val userchargeratemaster: JsonElement,
    val pagination:Pagination,
    val baselocations: JsonElement,
    val subbaselocations: JsonElement,
    val property: JsonElement,
    val properties: JsonElement,
    val propertyDemand: JsonElement,
    val propertyrequireddocument: JsonElement,
    val payments: JsonElement?,
    val payment: JsonElement?,
    val productConfiguration: JsonElement,
    val tcDashboardData: JsonElement,
    val propertyType: JsonElement,
    val roadType: JsonElement,
    val financialYear: JsonElement,
    val floorType: JsonElement,
    val constructionType: JsonElement,
    val floorRatableArea: JsonElement,
    val usageType: JsonElement,
    val occupancyType: JsonElement,
    val paymentGateway: JsonElement,
    val hdfcPayment: JsonElement,
    val tradeLicense: JsonElement,
    val txtnId: String,
    val tnxId: String,
    val makePayment: JsonElement,
)
