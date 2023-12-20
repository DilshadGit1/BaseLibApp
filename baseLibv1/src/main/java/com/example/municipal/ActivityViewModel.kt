package com.example.municipal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.content.SharedPreferences
import android.location.*
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.municipal.data.network.ApiServiceImpl
import com.example.municipal.data.network.BaseResponse
import com.example.municipal.data.network.RetrofitService
import com.example.municipal.model.AccessModel
import com.example.municipal.model.ProductConfiguration
import com.example.municipal.model.RootResponse
import com.example.municipal.model.User
import com.example.municipal.util.MyJsonUtil
import com.example.municipal.util.SharePrefranceHelper
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.net.URI.create
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(private val apiServiceImpl: ApiServiceImpl) : ViewModel(){
    val aclLiveData = MutableLiveData<BaseResponse<Any>>()
    val filterItemResult = MutableLiveData<BaseResponse<Any>>()
    val locationResult = MutableLiveData<BaseResponse<Any>>()
    val showHideToolbarLiveData =MutableLiveData<Int>()
    val prodConfigLiveData =MutableLiveData<BaseResponse<Any>>()
    var accessModel: AccessModel?=null
    var wardAccess: JsonArray?=null
    var userModel: User?=null


    private lateinit var apiInterface: RetrofitService
    fun getApiService(context: Context): RetrofitService {
        if(this::apiInterface.isInitialized && !MainApp.baseUrlChanged){
            return apiInterface
        }
        apiInterface= apiServiceImpl.getDynamicApiService()
        if(MainApp.baseUrlChanged){
            MainApp.baseUrlChanged=false
        }
        return apiInterface
    }
    fun retriveSaveAcl(sharedPreferences: SharedPreferences){
        val json: String = sharedPreferences.getString("acl", "")?:""
        if(json.isNotEmpty()) {
            val gson = Gson()
            val obj = gson.fromJson(json, AccessModel::class.java) as AccessModel
            accessModel = obj
        }
    }
    fun getProductConfig(context: Context):ProductConfiguration?{
        return SharePrefranceHelper.getProductConfig(context)
    }

    val INTERVAL = 1000L
    val FASTEST_INTERVAL = 500L

//    fun startLocationUpdate(activity: Activity){
//        if(isLocationEnabled(activity)){
//            initLocation(activity)
//        }else{
//            initLocation(activity)
//            turnOnLocation(activity)
//        }
//    }

     fun isLocationEnabled(activity: Activity): Boolean {
        val locationManager: LocationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }
     fun turnOnLocation(activity: Activity) {
//        val locationRequest: LocationRequest = LocationRequest.create()
//            .apply {
//                interval = INTERVAL
//                fastestInterval = FASTEST_INTERVAL
//                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//            }
//        val builder = LocationSettingsRequest.Builder()
//            .addLocationRequest(locationRequest)
//        val locationSettingsRequest = builder.build()

//        LocationServices.getSettingsClient(activity)
//            .checkLocationSettings(locationSettingsRequest)
//            .addOnSuccessListener(activity) {
//                // GPS enabled already
////                OnGpsListener?.locationStatus(true)
//            }
//            .addOnFailureListener(activity) { e ->
//                when ((e as ApiException).statusCode) {
//                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
//
//                        try {
//                            val rae = e as ResolvableApiException
//                            rae.startResolutionForResult(
//                                activity,
//                                200
//                            )
//                        } catch (sie: IntentSender.SendIntentException) {
//                            Log.i("TAG", "PendingIntent unable to execute request.")
//                        }
//
//                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
//                        val errorMessage =
//                            "Enable location services from settings."
//                        Log.e("TAG", errorMessage)
//
//                        Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//    }
//    @SuppressLint("MissingPermission")
//    public fun initLocation(activity: Activity) {
//        mFusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
//            val location: Location? = task.result
//            if (location != null) {
//                locationResult.value=BaseResponse.Success(location)
//                val geocoder = Geocoder(activity, Locale.getDefault())
//                val list: List<Address> =
//                    geocoder.getFromLocation(location.latitude, location.longitude, 1)
////                mainBinding.apply {
////                    tvLatitude.text = "Latitude\n${list[0].latitude}"
////                    tvLongitude.text = "Longitude\n${list[0].longitude}"
////                    tvCountryName.text = "Country Name\n${list[0].countryName}"
////                    tvLocality.text = "Locality\n${list[0].locality}"
////                    tvAddress.text = "Address\n${list[0].getAddressLine(0)}"
////                }
//            }else {
//                locationResult.value = BaseResponse.Error("Location Error")
//            }
//        }
    }

    fun <T>onePathGet(context: Context,
                      path: String,id: String,resCode:Int,
                      pojoClass:T,resMainKey: String) {

        aclLiveData.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val apiService =getApiService(context)
                val response = apiService.onePathGet(MainApp.municipalApiPath,path,id)

                if(response.isSuccessful){
                    if (response.body() != null) {
                        val obj=parseData(response.body()!!,pojoClass,resMainKey)
                        aclLiveData.value = BaseResponse.SuccessWithResCode(obj,resCode)
                    } else {
                        aclLiveData.value = BaseResponse.ErrorWithCode(response.errorBody().toString(),resCode)
                    }
                }else{
                    aclLiveData.value = BaseResponse.ErrorWithCode(response.message(),resCode)
                }


            } catch (ex: Exception) {
                aclLiveData.value = BaseResponse.ErrorWithCode(ex.message,resCode)
            }
        }
    }
    fun <T>getACL(context: Context, userTypeId: String,resCode:Int,
                      pojoClass:T,resMainKey: String) {

        aclLiveData.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val apiService =getApiService(context)
                val response = apiService.getACL(userTypeId)

                if(response.isSuccessful){
                    if (response.body() != null) {
//                        val obj=parseData(response.body()!!,pojoClass,resMainKey)
                        aclLiveData.value = BaseResponse.SuccessWithResCode(response.body(),resCode)
                    } else {
                        aclLiveData.value = BaseResponse.ErrorWithCode(response.errorBody().toString(),resCode)
                    }
                }else{
                    aclLiveData.value = BaseResponse.ErrorWithCode(response.message(),resCode)
                }


            } catch (ex: Exception) {
                aclLiveData.value = BaseResponse.ErrorWithCode(ex.message,resCode)
            }
        }
    }

    fun prodConfigApi(context: Context,resCode:Int) {

        prodConfigLiveData.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {

                val apiService =getApiService(context)
                val response = apiService.configure(MainApp.municipalApiPath)

                if(response.isSuccessful){
                    if (response.body() != null) {
//                        val obj=parseData(response.body()!!,pojoClass,resMainKey)
                        prodConfigLiveData.value = BaseResponse.SuccessWithResCode(response.body(),resCode)
                    } else {
                        prodConfigLiveData.value = BaseResponse.ErrorWithCode(response.errorBody().toString(),resCode)
                    }
                }else{
                    prodConfigLiveData.value = BaseResponse.ErrorWithCode(response.message(),resCode)
                }


            } catch (ex: Exception) {
                prodConfigLiveData.value = BaseResponse.ErrorWithCode(ex.message,resCode)
            }
        }
    }

    fun <T>parseData(rootResponse: RootResponse, mClass :T, resMainKey:String):Any{
        try {
            val jsonStr= MyJsonUtil.getStrFromPojo(rootResponse.response.data)
            val jsonElement=
                MyJsonUtil.getPojoFromStr(JsonElement::class.java,jsonStr) as JsonElement
            val str= MyJsonUtil.getPojoFromData(jsonElement,mClass,resMainKey)
            return str as Class<*>
        }catch (e:Exception){
            e.printStackTrace()
            return ""
        }
    }
}