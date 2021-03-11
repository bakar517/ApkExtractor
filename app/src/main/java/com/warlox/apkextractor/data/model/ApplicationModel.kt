package com.warlox.apkextractor.data.model

import android.graphics.drawable.Drawable

data class ApplicationModel(val appName:String, val appBundleId:String, var icon: Drawable?=null, val versionName:String, val versionCode:Int, val isSystemApp:Boolean)