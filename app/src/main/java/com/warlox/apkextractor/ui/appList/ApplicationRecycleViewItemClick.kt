package com.warlox.apkextractor.ui.appList

import com.warlox.apkextractor.data.model.ApplicationModel

interface ApplicationRecycleViewItemClick {
    fun onApplicationListItemClick(applicationModel: ApplicationModel)
}