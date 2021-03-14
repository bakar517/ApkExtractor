package com.warlox.apkextractor.util.extension

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.WindowManager
import android.widget.Toast
import com.warlox.apkextractor.R
import com.warlox.apkextractor.util.IntentParams


inline fun <reified T : Activity> Activity.navigate(params: List<IntentParams> = emptyList(), clearStack: Boolean = false) {
    val intent = Intent(this, T::class.java)
    for (parameter in params) {
        intent.putExtra(parameter.key, parameter.value)
    }
    if (clearStack) {
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    startActivity(intent)
}


fun Activity.blockInput() {
    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.unBlockInput() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun Activity.goToSetting(packageName: String) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.fromParts("package", packageName, null)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

fun Activity.browse(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    startActivity(intent)
}

fun Activity.copyTextToClip(text: String, showToast: Boolean = false) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("", text)
    clipboard.setPrimaryClip(clip)
    if (showToast) {
        toast(getString(R.string.copied_to_clipboard))
    }
}

fun Activity.toast(message: String) = Toast.makeText(this.applicationContext,
        message, Toast.LENGTH_SHORT).show()

