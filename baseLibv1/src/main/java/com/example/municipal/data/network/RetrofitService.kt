package com.example.municipal.data.network

import com.example.municipal.model.RootResponse
import com.google.gson.JsonObject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @POST("{path0}/dashboard/filter/funded")
    fun getDashboardFund(@Path("path0") path0:String,@Body request: JsonObject) : Call<RootResponse>

    @POST("{path0}/dashboard/filter/project")
    fun getDashboardProject(@Path("path0") path0:String,@Body request: JsonObject) : Call<RootResponse>

    @POST("{path0}/project/filter")
    fun getCurrentProject(@Path("path0") path0:String,@Body request: JsonObject) : Call<RootResponse>

    @POST("{path0}/user/auth")
    suspend fun login(@Path("path0") path0:String,@Body request: JsonObject) : Response<RootResponse>

    @GET("{path0}/product/configuration")
    suspend fun configure(@Path("path0") path0: String) : Response<RootResponse>

    @POST("{path0}/consumer/login/validateotp")
    suspend fun otpVerify(@Path("path0") path0:String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}/filter?pageno=no")
    suspend fun onePathFilter(@Path("path0") path0:String,@Path("path1") string: String, @Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}/filter")
    suspend fun onePathFilterWithPagination(@Path("path0") path0:String,@Path("path1") string: String, @Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}/filter")
    suspend fun onePathFilterWithPage(@Path("path0") path0:String,@Path("path1") string: String, @Body loginRequest: JsonObject,@Query("pageno") pageno:String) : Response<RootResponse>

    @POST("{path0}/{path1}/{path2}/filter")
    suspend fun twoPathFilterWithPage(@Path("path0") path0:String,@Path("path1") path2: String,@Path("path2") string: String, @Body loginRequest: JsonObject,@Query("pageno") pageno:String) : Response<RootResponse>

    @POST("{path1}/filter")
    suspend fun baseLocationapi(@Path("path1") string: String, @Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("test")
    suspend fun test( @Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}")
    suspend fun onePathSave( @Path("path0") path0:String,@Path("path1") string: String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}")
    suspend fun onePathPostWithQuery(@Path("path0") path0:String, @Path("path1") string: String,@Query("appVersion") id: Int,@Body loginRequest: JsonObject) : Response<RootResponse>

    @PUT("{path0}/{path1}")
    suspend fun onePathPut(@Path("path0") path0:String, @Path("path1") string: String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}/{path2}/filter?pageno=no")
    suspend fun twoPathPost(@Path("path0") path0:String, @Path("path1") string: String,@Path("path2") path2: String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @PUT("{path0}/user/{path1}/{path2}")
    suspend fun twoPathPut(@Path("path0") path0:String, @Path("path1") string: String,@Path("path2") path2: String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @PUT("{path0}/{path1}/{path2}")
    suspend fun twoPathPutWithoutUser(@Path("path0") path0:String, @Path("path1") string: String,@Path("path2") path2: String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @DELETE("{path0}/{path1}/{path2}/{path3}")
    suspend fun threePathDelete(@Path("path0") path0:String, @Path("path1") string: String,@Path("path2") path2: String,@Path("path3") path3: String,@Query("id") id: String) : Response<RootResponse>

    @GET("{path0}/{path1}/{path2}/{path3}")
    suspend fun threePathGet(@Path("path0") path0:String, @Path("path1") string: String,@Path("path2") path2: String,@Path("path3") path3: String,@Query("id") id: String) : Response<RootResponse>

    @GET("{path0}/property/{path1}")
    suspend fun onePathPropertyGet(@Path("path0") path0:String,@Path("path1") string: String,@Query("id") id:String) : Response<RootResponse>

    @GET("{path0}/{path1}")
    suspend fun onePathGet(@Path("path0") path0:String,@Path("path1") string: String,@Query("id") id:String) : Response<RootResponse>

    @GET("{path0}/{path1}/{path2}")
    suspend fun twoPathGet(
        @Path("path0") path0: String,
        @Path("path1") string: String,
        @Path("path2") string2: String
    ) : Response<RootResponse>

    @GET("{path0}/{path1}/{path2}")
    suspend fun twoPathGet(@Path("path0") path0:String,@Path("path1") string: String,@Path("path2") string2: String,@Query("id") id:String) : Response<RootResponse>

    @POST("{path1}/{path2}")
    suspend fun twoPathPostNoFilter( @Path("path1") string: String,@Path("path2") path2: String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path1}/{path2}")
    suspend fun twoPathPostWithPage( @Path("path1") string: String,@Path("path2") path2: String,@Query("pageno") pageno:String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}/{path2}/{path3}")
    suspend fun threePathPost( @Path("path0") path0: String,@Path("path1") string: String,@Path("path2") path2: String,@Path("path3") path3: String,@Body loginRequest: JsonObject) : Response<RootResponse>

    @POST("{path0}/{path1}/{path2}/{path3}")
    suspend fun threePathPostWithPageNo( @Path("path0") path0: String,@Path("path1") string: String,@Path("path2") path2: String,@Path("path3") path3: String,@Body loginRequest: JsonObject, @Query("pageno")  pageno:String) : Response<RootResponse>

    @PUT("{path0}/{path1}/{path2}/{path3}")
    suspend fun threePathPut( @Path("path0") path0: String,@Path("path1") string: String,@Path("path2") path2: String,@Path("path3") path3: String,@Body loginRequest: JsonObject, @Query("id")  id:String) : Response<RootResponse>

    @DELETE("{path1}/{path2}/{path3}/{path4}")
    suspend fun fourPathDelete(
        @Path("path1") string: String?,
        @Path("path2") path2: String?,
        @Path("path3") path3: String?,
        @Path("path4") path4: String?,
        @Query("id") id: String?
    ): Response<RootResponse>

    @PUT("{path1}/{path2}/{path3}/{path4}")
    suspend fun fourPathPut(
        @Path("path1") string: String?,
        @Path("path2") path2: String?,
        @Path("path3") path3: String?,
        @Path("path4") path4: String?,
        @Body jsonObject: JsonObject?,
        @Query("id") id: String?,
    ): Response<RootResponse>

    @POST("{path1}/{path2}/{path3}/{path4}")
    suspend fun fourPathPostWithPage(
        @Path("path1") string: String?,
        @Path("path2") path2: String?,
        @Path("path3") path3: String?,
        @Path("path4") path4: String?,
        @Query("pageno") pageno: String?,
        @Body jsonObject: JsonObject?
    ): Response<RootResponse>



    @GET("{path1}/{path2}/{path3}/{path4}")
    suspend fun getPayment(
        @Path("path1") string: String?,
        @Path("path2") path2: String?,
        @Path("path3") path3: String?,
        @Path("path4") path4: String?,
        @Query("tnxId") id: String?
    ): Response<RootResponse>

    @GET("{path1}/{path2}/{path3}")
    suspend fun getPayment(
        @Path("path1") string: String?,
        @Path("path2") path2: String?,
        @Path("path3") path3: String?,
        @Query("tnxId") id: String?
    ): Response<RootResponse>


    @GET("acl/access")
    suspend fun getACL(
        @Query("userTypeId") userTypeId: String?
    ): Response<RootResponse>

    @Multipart
    @POST("{path0}/common/docsupload/{path}")
    suspend fun singleFileUpload(
        @Part file: List<MultipartBody.Part>?,
        @Path("path0") path0:String,@Path("path") path: String?
    ): Response<RootResponse>


    @Multipart
    @POST("{path0}/common/docsupload/{path}")
    suspend fun multiFileUpload(
        @Part file: List<MultipartBody.Part>?,
        @Path("path0") path0:String,@Path("path") path: String?
    ): Response<RootResponse>
}