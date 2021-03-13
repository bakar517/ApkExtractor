package com.warlox.apkextractor.repo

import android.content.Context
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.util.ApplicationUtil
import javax.inject.Inject

class AppsProviderImp @Inject constructor(private val context: Context) : AppsProvider {

    override suspend fun getListOfAllApplication(): List<ApplicationModel> {
        return ApplicationUtil.getListOfUserInstalledApplication(context)
    }

}