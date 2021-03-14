package com.warlox.apkextractor.util.extension

import java.text.SimpleDateFormat
import java.util.*


fun Date.toThisFormat(format: String): String {
    val dateFormat = SimpleDateFormat(format)
    return dateFormat.format(this)
}