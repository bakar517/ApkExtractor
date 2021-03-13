package com.warlox.apkextractor.repo

import com.warlox.apkextractor.data.model.ApplicationModel

interface AppsProvider {
    suspend fun getListOfAllApplication(): List<ApplicationModel>
}