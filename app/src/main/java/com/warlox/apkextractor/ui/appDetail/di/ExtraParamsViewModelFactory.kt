package com.warlox.apkextractor.ui.appDetail.di

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.ui.appDetail.AppDetailViewModel

class ExtraParamsViewModelFactory(
        private val packageManager: PackageManager,
        private val appPackageName: String
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            AppDetailViewModel(packageManager, appPackageName) as T
}