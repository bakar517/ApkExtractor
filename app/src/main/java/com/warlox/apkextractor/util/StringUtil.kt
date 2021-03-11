package com.warlox.apkextractor.util

import android.content.Context
import androidx.annotation.StringRes
import java.lang.StringBuilder

class StringUtil private constructor(){

    companion object{
        fun appendToBuilder(builder:StringBuilder, value:String){
            builder.append(value)
        }

        fun appendWithNewLine(builder:StringBuilder, value:String){
            builder.append(value)
            appendNewLine(builder)
        }

        fun appendSpace(builder:StringBuilder){
            builder.append(" ")
        }

        fun appendNewLine(builder:StringBuilder){
            builder.append("\n")
        }

        fun getStringFromRes(context: Context, @StringRes id:Int):String{
            return context.getString(id)
        }
    }
}