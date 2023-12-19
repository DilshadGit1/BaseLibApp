package com.example.municipal.data.network

sealed class BaseResponse<out T> {
    data class Success<out T>(val data: T? = null) : BaseResponse<T>()

    data class SuccessWithResCode<out T>(val data: T? = null,val code:Int) : BaseResponse<T>()
    data class SuccessWithCode<out T>(val data: T? = null,val code:Int) : BaseResponse<T>()
    data class Loading(val nothing: Nothing?=null) : BaseResponse<Nothing>()
    data class STARTED(val nothing: Nothing?=null) : BaseResponse<Nothing>()
    data class Error(val msg: String?) : BaseResponse<Nothing>()
    data class ErrorWithCode(val msg: String?,val code:Int) : BaseResponse<Nothing>()
    data class Update(val name:String,val id: Int,val action:Int,val isSuccess:Boolean) : BaseResponse<Nothing>()
    data class Filter<out T>(val name:String,val id: Int,val action:Int,val data: T? = null) : BaseResponse<T>()
}
