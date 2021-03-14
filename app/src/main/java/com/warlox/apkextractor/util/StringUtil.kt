package com.warlox.apkextractor.util

import android.content.Context
import androidx.annotation.StringRes

fun getStringFromRes(context: Context, @StringRes id: Int): String {
    return context.getString(id)
}
