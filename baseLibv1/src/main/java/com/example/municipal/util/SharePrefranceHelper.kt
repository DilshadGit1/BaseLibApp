package com.example.municipal.util

import android.content.Context
import com.example.baselibv1.R
import com.example.municipal.model.ProductConfiguration

class SharePrefranceHelper() {

    companion object{
       fun getBaseUrl(context: Context): String? {
           val sp=context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
          return sp.getString("BaseUrl","http://test.com");
       }
        fun setBaseUrlAndId(context: Context,id:String,url:String) {
            val sp=context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)
             sp.edit().putString("BaseUrl",url).putString("uniqueId",id).apply()
        }

        fun getProductConfig(context: Context): ProductConfiguration? {
            val sp = context.getSharedPreferences("loginpref", Context.MODE_PRIVATE)
            val str = sp.getString("ProductConfig_SP", "")
            if(str?.isEmpty() == true){
                return null
            }
            return MyJsonUtil.getPojoFromStr(
                ProductConfiguration::class.java,
                str
            ) as ProductConfiguration?
        }

        fun saveProductConfigInSP(context: Context, model: ProductConfiguration?) {
            val sp = context.getSharedPreferences("loginpref", Context.MODE_PRIVATE)
            sp.edit().putString("ProductConfig_SP", MyJsonUtil.getStrFromPojo(model)).apply()
        }


    }
}