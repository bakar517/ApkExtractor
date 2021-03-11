package com.warlox.apkextractor.view

import android.app.Application
import android.content.pm.ApplicationInfo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.warlox.apkextractor.view.viewmodel.AppDetailViewModel
import com.warlox.apkextractor.view.viewmodel.HomeScreenViewModel

class MyViewModelFactory(private val application: Application, private val applicationInfo: ApplicationInfo?): ViewModelProvider.Factory {
    constructor(application: Application): this(application, null)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            return HomeScreenViewModel(application) as T
        }
        if (modelClass.isAssignableFrom(AppDetailViewModel::class.java)) {
            return AppDetailViewModel(application, applicationInfo!!) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}