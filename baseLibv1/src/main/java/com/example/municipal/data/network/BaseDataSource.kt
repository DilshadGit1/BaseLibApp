package com.example.municipal.data.network

import com.google.gson.Gson
import retrofit2.Response

abstract class BaseDataSource {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): BaseResponse<T> {

        try {
            val response = apiCall()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return BaseResponse.Success(body)
                }
            }else{

                return BaseResponse.Error("body null")
            }

            return BaseResponse.Error("Something went wrong, try again jaa")

        } catch (e: Exception) {
            return BaseResponse.Error("Something went wrong, $e")
        }
    }


}