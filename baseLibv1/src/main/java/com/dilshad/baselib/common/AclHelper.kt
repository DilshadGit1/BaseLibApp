package com.dilshad.baselib.common

import android.view.View
import com.dilshad.baselib.model.AccessModel

class AclHelper( var accessModel: AccessModel?) {


    init {

    }

//    fun getModuleStatusById(uniqueId:String):Boolean{
//        for(item in accessModel.moduleAccess){
//            if(item.uniqueId==uniqueId){
//                return item.access.check.equals("Yes")
//            }
//        }
//        return false
//    }
//    fun getMenuStatusById(uniqueId:String):Boolean{
//        for(item in accessModel.menuAccess){
//            if(item.uniqueId==uniqueId){
//                return item.access.check.equals("Yes")
//            }
//        }
//        return false
//    }
    fun setACLModule(uniqueId:String,view: View){
        if(accessModel?.ModuleAccess==null){
            return
        }
        for(item in accessModel!!.ModuleAccess){
            if(item?.uniqueId==uniqueId){
                if(item.access?.check.equals("Yes")){
                    view.visibility=View.VISIBLE
                }else{
                    view.visibility=View.GONE
                }
            }
        }
    }
    fun setACLMenu(uniqueId:String,view: View){
        if(accessModel?.MenuAccess==null){
            return
        }
        for(item in accessModel!!.MenuAccess!!){
            if(item?.uniqueId==uniqueId){
               if(item.access?.check.equals("Yes")){
                   view.visibility=View.VISIBLE
               }else{
                   view.visibility=View.GONE
               }
            }
        }
    }
    fun setACLTab(uniqueId:String,view: View){
        if(accessModel?.TabAccess==null){
            return
        }
        for(item in accessModel!!.TabAccess!!){
            if(item?.uniqueId==uniqueId){
                if(item.access?.check.equals("Yes")){
                    view.visibility=View.VISIBLE
                }else{
                    view.visibility=View.GONE
                }
            }
        }
    }
    fun getACLTabVisibility(uniqueId:String):Boolean{
        if(accessModel?.TabAccess==null){
            return false
        }
        for(item in accessModel!!.TabAccess!!){
            if(item?.uniqueId==uniqueId){
                return item.access?.check.equals("Yes")
            }
        }
        return false
    }
    fun getACLActionStatus(uniqueId:String):Boolean{
        if(accessModel?.FeatureAccess==null){
            return false
        }
        for(item in accessModel!!.FeatureAccess!!){
            if(item?.uniqueId==uniqueId){
                if(item.access?.check.equals("Yes")){
                    return true
                }
            }
        }
        return false
    }
}