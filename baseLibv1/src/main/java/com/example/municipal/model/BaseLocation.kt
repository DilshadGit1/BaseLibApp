package com.example.municipal.model

 data class BaseLocation(
    val id: String,
    val uniqueId: String,
    val name: String,
    val baseURL: String,
//    val createdOn: CreatedOn,
//    val updated: Updated,
    val updatedLog: Any?,
    val status: String,
    val ref: Map<String, Any>)
