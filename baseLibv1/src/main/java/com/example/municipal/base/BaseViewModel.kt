package com.example.municipal.base

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.municipal.data.network.BaseResponse
import com.example.municipal.data.network.MainRepository
import com.example.municipal.data.network.RetrofitMunicipalRepo
import com.example.municipal.model.AccessModel
import com.example.municipal.model.RootResponse
import com.example.municipal.util.FileUtil
import com.example.municipal.util.MyJsonUtil
import com.google.gson.Gson
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull

import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class BaseViewModel @Inject constructor(
    application: Application,
    @RetrofitMunicipalRepo val mainRepository: MainRepository
) : AndroidViewModel(application) {

//    private val _fileUpload: MutableLiveData<BaseResponse<Any>> = MutableLiveData()
//    val fileUploadLiveData: LiveData<BaseResponse<Any>> = _fileUpload

    private val _fileUpload = MutableStateFlow<BaseResponse<RootResponse>>(BaseResponse.STARTED())
    val fileUploadLiveData: StateFlow<BaseResponse<RootResponse>> = _fileUpload


     var accessModel: AccessModel?=null

    fun retriveSaveAcl(sharedPreferences: SharedPreferences){
        val json: String = sharedPreferences.getString("acl", "")?:""
        if(json.isNotEmpty()) {
            val gson = Gson()
            val obj = gson.fromJson(json, AccessModel::class.java) as AccessModel
            accessModel = obj
        }
    }
    fun singleFileUpload(
        bytes: ByteArray,
        path: String
    ) {

        _fileUpload.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val arr: ByteArray = FileUtil.compressImageByteArray(bytes)
                val builder = MultipartBody.Builder()
                builder.setType(MultipartBody.FORM)
                val compressbody =
                    RequestBody.create("multipart/form-data".toMediaTypeOrNull(), arr)
                builder.addFormDataPart("uploadfile", "file_", compressbody)
                val requestBody: List<MultipartBody.Part> = builder.build().parts

                mainRepository.singleFileUpload(requestBody, path)
                    .catch {
                        _fileUpload.value = BaseResponse.Error(it.message)
                    }
                    .collect {
                        _fileUpload.value = it
                    }




            } catch (ex: Exception) {
                _fileUpload.value = BaseResponse.Error(ex.message)
            }
        }
    }
    fun parseData(rootResponse: RootResponse):String{
        val str= MyJsonUtil.getJsonFromPojoObj(rootResponse.response.data)
        return JsonParser().parse(str).getAsJsonObject().get("uri").asString
    }
}