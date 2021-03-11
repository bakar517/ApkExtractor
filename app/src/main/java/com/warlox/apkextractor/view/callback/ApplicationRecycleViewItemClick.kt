package com.warlox.apkextractor.view.callback

import com.warlox.apkextractor.data.model.ApplicationModel

interface ApplicationRecycleViewItemClick {
    fun onApplicationListItemClick(applicationModel: ApplicationModel)
}