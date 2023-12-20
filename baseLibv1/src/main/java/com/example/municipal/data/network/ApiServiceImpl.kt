package com.example.municipal.data.network

import com.example.baselibv1.BuildConfig
import com.example.municipal.model.RootResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val baseUrlProvider: BaseUrlProvider
) : RetrofitService {
    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null || retrofit?.baseUrl().toString() != baseUrlProvider.getBaseUrl()) {

            val interceptor =  HttpLoggingInterceptor();
            if(BuildConfig.DEBUG) {
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            }
            val okHttpClient= OkHttpClient.Builder().addInterceptor(interceptor)
            okHttpClient.connectTimeout(120, TimeUnit.SECONDS)
            okHttpClient.readTimeout(120, TimeUnit.SECONDS)
            okHttpClient.writeTimeout(120, TimeUnit.SECONDS)

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrlProvider.getBaseUrl())
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    @RetrofitMunicipalService
    private val apiService: RetrofitService by lazy {
        getRetrofit().create(RetrofitService::class.java)
    }
    public fun getDynamicApiService():RetrofitService{
        return getRetrofit().create(RetrofitService::class.java)
    }

    override fun getDashboardFund(path0: String, request: JsonObject): Call<RootResponse> {
       return getDynamicApiService().getDashboardFund(path0,request)
    }

    override fun getDashboardProject(path0: String, request: JsonObject): Call<RootResponse> {
        return getDynamicApiService().getDashboardProject(path0, request)
    }

    override fun getCurrentProject(path0: String, request: JsonObject): Call<RootResponse> {
        return getDynamicApiService().getCurrentProject(path0, request)
    }

    override suspend fun login(path0: String, request: JsonObject): Response<RootResponse> {
        return getDynamicApiService().login(path0, request)
    }

    override suspend fun configure(path0: String): Response<RootResponse> {
        return getDynamicApiService().configure(path0)
    }

    override suspend fun otpVerify(
        path0: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().otpVerify(path0, loginRequest)
    }

    override suspend fun onePathFilter(
        path0: String,
        string: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().onePathFilter(path0, string, loginRequest)
    }

    override suspend fun onePathFilterWithPagination(
        path0: String,
        string: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().onePathFilterWithPagination(path0, string, loginRequest)
    }

    override suspend fun onePathFilterWithPage(
        path0: String,
        string: String,
        loginRequest: JsonObject,
        id: String
    ): Response<RootResponse> {
        return getDynamicApiService().onePathFilterWithPage(path0, string, loginRequest,id)
    }

    override suspend fun twoPathFilterWithPage(
        path0: String,
        path2: String,
        string: String,
        loginRequest: JsonObject,
        pageno: String
    ): Response<RootResponse> {
        return getDynamicApiService().twoPathFilterWithPage(path0, path2, string, loginRequest, pageno)
    }

    override suspend fun baseLocationapi(
        string: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().baseLocationapi(string, loginRequest)
    }

    override suspend fun test(loginRequest: JsonObject): Response<RootResponse> {
        return getDynamicApiService().test(loginRequest)
    }

    override suspend fun onePathSave(
        path0: String,
        string: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().onePathSave(path0, string, loginRequest)
    }

    override suspend fun onePathPostWithQuery(
        path0: String,
        string: String,
        id: Int,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().onePathPostWithQuery(path0, string, id, loginRequest)
    }

    override suspend fun onePathPut(
        path0: String,
        string: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().onePathPut(path0, string, loginRequest)
    }

    override suspend fun twoPathPost(
        path0: String,
        string: String,
        path2: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().twoPathPost(path0, string, path2, loginRequest)
    }

    override suspend fun twoPathPut(
        path0: String,
        string: String,
        path2: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().twoPathPut(path0, string, path2, loginRequest)
    }

    override suspend fun twoPathPutWithoutUser(
        path0: String,
        string: String,
        path2: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun threePathDelete(
        path0: String,
        string: String,
        path2: String,
        path3: String,
        id: String
    ): Response<RootResponse> {
        return getDynamicApiService().threePathDelete(path0, string, path2, path3, id)
    }

    override suspend fun threePathGet(
        path0: String,
        string: String,
        path2: String,
        path3: String,
        id: String
    ): Response<RootResponse> {
        return getDynamicApiService().threePathGet(path0, string, path2, path3, id)
    }

    override suspend fun onePathPropertyGet(
        path0: String,
        string: String,
        id: String
    ): Response<RootResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun onePathGet(
        path0: String,
        string: String,
        id: String
    ): Response<RootResponse> {
        return getDynamicApiService().onePathGet(path0, string, id)
    }

    override suspend fun twoPathGet(
        path0: String,
        string: String,
        string2: String
    ): Response<RootResponse> {
        return getDynamicApiService().twoPathGet(path0, string, string2)
    }

    override suspend fun twoPathGet(
        path0: String,
        string: String,
        string2: String,
        id: String
    ): Response<RootResponse> {
        return getDynamicApiService().twoPathGet(path0, string, string2, id)
    }

    override suspend fun twoPathPostNoFilter(
        string: String,
        path2: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().twoPathPostNoFilter(string, path2, loginRequest)
    }

    override suspend fun twoPathPostWithPage(
        string: String,
        path2: String,
        pageno: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().twoPathPostWithPage(string, path2, pageno, loginRequest)
    }

    override suspend fun threePathPost(
        path0: String,
        string: String,
        path2: String,
        path3: String,
        loginRequest: JsonObject
    ): Response<RootResponse> {
        return getDynamicApiService().threePathPost(path0, string, path2, path3, loginRequest)
    }

    override suspend fun threePathPostWithPageNo(
        path0: String,
        string: String,
        path2: String,
        path3: String,
        loginRequest: JsonObject,
        pageno: String
    ): Response<RootResponse> {
        return getDynamicApiService().threePathPostWithPageNo(path0, string, path2, path3, loginRequest, pageno)
    }

    override suspend fun threePathPut(
        path0: String,
        string: String,
        path2: String,
        path3: String,
        loginRequest: JsonObject,
        id: String
    ): Response<RootResponse> {
       return getDynamicApiService().threePathPut(path0, string, path2, path3, loginRequest, id)
    }

    override suspend fun fourPathDelete(
        string: String?,
        path2: String?,
        path3: String?,
        path4: String?,
        id: String?
    ): Response<RootResponse> {
        return getDynamicApiService().fourPathDelete(string, path2, path3, path4, id)
    }

    override suspend fun fourPathPut(
        string: String?,
        path2: String?,
        path3: String?,
        path4: String?,
        jsonObject: JsonObject?,
        id: String?
    ): Response<RootResponse> {
        return getDynamicApiService().fourPathPut(string, path2, path3, path4,jsonObject, id)
    }

    override suspend fun fourPathPostWithPage(
        string: String?,
        path2: String?,
        path3: String?,
        path4: String?,
        pageno: String?,
        jsonObject: JsonObject?
    ): Response<RootResponse> {
        return getDynamicApiService().fourPathPostWithPage(string, path2, path3, path4, pageno, jsonObject)
    }

    override suspend fun getPayment(
        string: String?,
        path2: String?,
        path3: String?,
        path4: String?,
        id: String?
    ): Response<RootResponse> {
        return getDynamicApiService().getPayment(string, path2, path3, path4, id)
    }

    override suspend fun getPayment(
        string: String?,
        path2: String?,
        path3: String?,
        id: String?
    ): Response<RootResponse> {
        return getDynamicApiService().getPayment(string, path2, path3, id)
    }

    override suspend fun getACL(userTypeId: String?): Response<RootResponse> {
        return getDynamicApiService().getACL(userTypeId)
    }

    override suspend fun singleFileUpload(
        file: List<MultipartBody.Part>?,
        path0: String,
        path: String?
    ): Response<RootResponse> {
        return getDynamicApiService().singleFileUpload(file, path0, path)
    }

    override suspend fun multiFileUpload(
        file: List<MultipartBody.Part>?,
        path0: String,
        path: String?
    ): Response<RootResponse> {
        return getDynamicApiService().multiFileUpload(file, path0, path)
    }


}