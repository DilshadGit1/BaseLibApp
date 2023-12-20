package com.example.municipal.model

data class Address(val no: String,
                   val stateCode: String,
                   val districtCode: String,
                   val villageCode: String,
                   val zoneCode: String,
                   val wardCode: String,
                   val al1: String,
                   val al2: String,
                   val plotNo: String,
                   val khataNo: String,
                   val khasraNo: String,
                   val postalCode: String,
                   val landmark: String,

                   val location: Location)
data class Location(
    val type: String,
    val coordinates: Any?,
)