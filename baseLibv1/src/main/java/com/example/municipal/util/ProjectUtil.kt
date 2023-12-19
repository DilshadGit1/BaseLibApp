package com.example.municipal.util

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import org.joda.time.format.DateTimeFormat
import java.util.StringTokenizer

object ProjectUtil {
    fun convertPaytmDateIntoServerDateFormat(date: String?): String? {
        val dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.s")
        val jodatime1 = dtf.parseDateTime(date)
        //        DateTime dtf = ISODateTimeFormat.dateTimeParser().parseDateTime(date);
        return jodatime1.toString("yyyy-MM-dd'T'HH:mm:ss.ss'Z'")
    }
    fun amountInWord(amount: String): String {
        return amountInWord(amount.toFloat())
    }
    fun amountInWord(amount: Float): String {
        var newAmount = ""
        val deciaml = amount.toString()
        val tokens = StringTokenizer(deciaml, ".")
        val first = tokens.nextToken() // this will contain "d"
        val sec = tokens.nextToken() // this will contain "d"
        newAmount = if (deciaml.contains(".")) {
            val doblevalue = sec.toInt()
            val fvalue = first.toInt()
            "Rupees " + Words.convert(fvalue.toLong()) + " and " + Words.convert(
                doblevalue.toLong()
            )
        } else {
            "Rupees " + Words.convert(deciaml.toInt().toLong())
        }
        return newAmount
    }
    fun getWardAccess(accessPrivilege:JsonElement?):JsonArray{
        try{
            if(!accessPrivilege?.isJsonNull!!){
                return accessPrivilege.asJsonObject["wards"].asJsonArray
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return JsonArray()
    }
}