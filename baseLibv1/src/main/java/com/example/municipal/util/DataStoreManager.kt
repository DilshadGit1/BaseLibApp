package com.example.municipal.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.municipal.model.Address
import com.example.municipal.model.BaseLocation
import com.example.municipal.model.SimpleModel
import com.example.municipal.model.User
import kotlinx.coroutines.flow.map

class DataStoreManager (val context: Context) {



    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_DATASTORE")
        val ULB_NAME = stringPreferencesKey("ULB_NAME")
        val ULB_UNIQUEID = stringPreferencesKey("ULB_UNIQUEID")
        val ULB_baseURL = stringPreferencesKey("ULB_baseURL")

        val USER_MobNo = stringPreferencesKey("User_MobNumber")
        val User_UniqueId = stringPreferencesKey("User_UniqueId")
        val User_Name = stringPreferencesKey("User_Name")
        val User_father = stringPreferencesKey("User_Father")
        val User_Properties = stringPreferencesKey("User_Properties")
        val User_Type = stringPreferencesKey("User_Type")
        val Municipal_Name = stringPreferencesKey("Municupal_name")
        val USER_PROFILE = stringPreferencesKey("User_profile")
        val User_Gender = stringPreferencesKey("User_Gender")
        val User_Email = stringPreferencesKey("User_Email")
        val User_Dob = stringPreferencesKey("User_Dob")

    }
    suspend fun savetoDataStore(baseLocation: BaseLocation) {
        context.dataStore.edit {

            it[ULB_NAME] = baseLocation.name
            it[ULB_UNIQUEID] = baseLocation.uniqueId
            it[ULB_baseURL] = baseLocation.baseURL

        }
    }
    suspend fun saveProdConfigToDataStore(simpleModel: SimpleModel) {
        context.dataStore.edit {

            it[ULB_NAME] = simpleModel.name
            it[ULB_UNIQUEID] = simpleModel.uniqueId
            it[ULB_baseURL] = simpleModel.title

        }
    }
    suspend fun saveUsertoDataStore(user: User) {
        context.dataStore.edit {

            it[USER_MobNo] = user.mobile
            it[User_UniqueId] = user.uniqueId
            it[User_Type] = user.type
            it[User_Name] = user.userName
            it[Municipal_Name] = user.name
            it[USER_PROFILE] = user.profile
            it[User_father] = user.fatherRpanRhusband
            it[User_Gender] = user.gender
            it[User_Email] = user.email
            it[User_Dob] = user.dob

        }
    }
    suspend fun getProdConfig() = context.dataStore.data.map {
        SimpleModel(
            name = it[ULB_NAME]?:"",
            id = it[ULB_NAME]?:"",
            title = it[ULB_baseURL]?:"",
            uniqueId =it[ULB_UNIQUEID]?:"",
        )
    }

    suspend fun getFromDataStore() = context.dataStore.data.map {
        BaseLocation(
            name = it[ULB_NAME]?:"",
            uniqueId = it[ULB_UNIQUEID]?:"",
            baseURL = it[ULB_baseURL]?:"",
            id ="",
            ref = HashMap(),
            status = "",
            updatedLog = Any()
        )
    }
    suspend fun getUserFromDataStore() = context.dataStore.data.map {
        User(
            id = it[ULB_NAME]?:"",
            uniqueId = it[User_UniqueId]?:"",
            userName = it[User_Name]?:"",
            mobile = it[USER_MobNo]?:"",
            type = it[User_Type]?:"",
            name = it[Municipal_Name]?:"",
            profile=it[USER_PROFILE]?:"",
            fatherRpanRhusband = it[User_father]?:"",
            gender = it[User_Gender]?:"",
            email = it[User_Email]?:"",
            dob = it[User_Dob]?:"",
        )
    }

}