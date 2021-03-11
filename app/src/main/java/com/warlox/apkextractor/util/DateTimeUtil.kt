package com.warlox.apkextractor.util

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtil private constructor(){
    companion object{

        private const val DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

        fun getFormattedTime(time:Long):String{
            return getFormattedTime(time, DEFAULT_DATE_TIME_FORMAT)
        }
        fun getFormattedTime(time:Long, format:String):String{
            val dateFormat = SimpleDateFormat(format)
            val dateTime = Date(time)
            return dateFormat.format(dateTime)
        }

    }
}