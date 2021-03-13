package com.warlox.apkextractor.util

import androidx.recyclerview.widget.DiffUtil
import com.warlox.apkextractor.data.model.ApplicationModel

val ApplicationModelDiffUtil = object : DiffUtil.ItemCallback<ApplicationModel>() {
    override fun areItemsTheSame(oldItem: ApplicationModel, newItem: ApplicationModel)
            : Boolean {
        return oldItem.appBundleId == newItem.appBundleId &&
                oldItem.appName == newItem.appName &&
                oldItem.isSystemApp == newItem.isSystemApp
    }

    override fun areContentsTheSame(oldItem: ApplicationModel, newItem: ApplicationModel) =
            oldItem == newItem
}
