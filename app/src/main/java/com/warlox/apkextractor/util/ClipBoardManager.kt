package com.warlox.apkextractor.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE

internal class ClipBoardManager {

    companion object{
        internal const val KEY = "textCopyKey"
        fun copyToClipboard(context: Context, text:String){
            val clipBoardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipBoardManager.setPrimaryClip(ClipData.newPlainText(KEY, text))
        }

    }

}