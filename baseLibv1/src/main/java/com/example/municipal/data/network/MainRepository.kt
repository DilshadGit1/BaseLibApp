package com.example.municipal.data.network

import com.example.municipal.MainApp
import com.example.municipal.model.RootResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import javax.inject.Inject

class MainRepository @Inject constructor(@RetrofitMunicipalService private val retrofitMunicipalService: RetrofitService, @RetrofitMainService private val retrofitMainService: RetrofitService): BaseDataSource() {

    suspend fun singleFileUpload(files: List<MultipartBody.Part>,path: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.singleFileUpload(files,MainApp.municipalApiPath,path) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun login(loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.login(MainApp.municipalApiPath,loginRequest) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun configuration() : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.configure(MainApp.municipalApiPath) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun otpVerify(loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.otpVerify(MainApp.municipalApiPath,loginRequest) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun onePathFilter(path: String,loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathFilter(MainApp.municipalApiPath,path,loginRequest) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun onePathFilterWithPagination(path: String,loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathFilterWithPagination(MainApp.municipalApiPath,path,loginRequest) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun onePathFilterWithPage(path: String,pageNo:String,loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathFilterWithPage(MainApp.municipalApiPath,path,loginRequest,pageNo) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun twoPathFilterWithPage(path: String,path2:String,pageNo:String,loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathFilterWithPage(MainApp.municipalApiPath,path,path2,loginRequest,pageNo) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun baseLocation(path: String,loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMainService.baseLocationapi(path,loginRequest) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun subBaseLocation(path: String,loginRequest: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMainService.baseLocationapi(path,loginRequest) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun onePathPropertyGet(path: String,id: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathPropertyGet(MainApp.municipalApiPath,path,id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun onePathGet(path: String,id: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathGet(MainApp.municipalApiPath,path,id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun twoPathGet(path: String,path2: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathGet(MainApp.municipalApiPath,path,path2) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun twoPathGet(path: String,path2: String,id: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathGet(MainApp.municipalApiPath,path,path2,id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun twoPathPostNoFilter(path: String,path2: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathPostNoFilter(path,path2,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun twoPathPostWithPage(path: String,path2: String,pageNo: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathPostWithPage(path,path2,pageNo,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun threePathPost(path: String,path2: String,path3: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.threePathPost(MainApp.municipalApiPath,path,path2,path3,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun onePathSave(path: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathSave(MainApp.municipalApiPath,path,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun onePathPostWithQuery(path: String,id:Int,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathPostWithQuery(MainApp.municipalApiPath,path,id,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun onePathPut(path: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.onePathPut(MainApp.municipalApiPath,path,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun twoPathPost(path: String,path2: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathPost(MainApp.municipalApiPath,path,path2,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun twoPathPut(path: String,path2: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathPut(MainApp.municipalApiPath,path,path2,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun twoPathPutWithoutUser(path: String,path2: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.twoPathPutWithoutUser(MainApp.municipalApiPath,path,path2,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun threePathDelete(path: String,path2: String,path3: String,id: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.threePathDelete(MainApp.municipalApiPath,path, path2, path3, id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun threePathGet(path: String,path2: String,path3: String,id: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.threePathGet(MainApp.municipalApiPath,path, path2, path3, id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun threePathPost(path: String,path2: String,path3: String,pageNo: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.threePathPostWithPageNo(MainApp.municipalApiPath,path, path2, path3, jsonObject,pageNo) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun threePathPut(path: String,path2: String,path3: String,jsonObject: JsonObject,id: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.threePathPut(MainApp.municipalApiPath,path, path2, path3,jsonObject,id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fourPathPost(path: String,path2: String,path3: String,path4: String,pageNo: String,jsonObject: JsonObject) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.fourPathPostWithPage(path, path2, path3,path4, pageNo,jsonObject) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun fourPathPut(path: String,path2: String,path3: String,path4: String,jsonObject: JsonObject,id:String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.fourPathPut(path, path2, path3,path4,jsonObject, id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun getPayment(path: String,path2: String,path3: String,path4: String,id: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.getPayment(path, path2, path3,path4, id) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    suspend fun multiFileUpload(files: List<MultipartBody.Part>,path: String) : Flow<BaseResponse<RootResponse>> {
        return flow {
            val result = safeApiCall { retrofitMunicipalService.multiFileUpload(files,MainApp.municipalApiPath,path) }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
    }