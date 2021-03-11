package com.warlox.apkextractor.util

import androidx.recyclerview.widget.DiffUtil
import com.warlox.apkextractor.data.model.ApplicationModel

class ApplicationListDiffUtil(private val oldApplicationModelList:List<ApplicationModel>, private val newApplicationModelList:List<ApplicationModel>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldApplicationModelList[oldItemPosition].appBundleId == newApplicationModelList[newItemPosition].appBundleId
    }
    override fun getOldListSize(): Int = oldApplicationModelList.size

    override fun getNewListSize(): Int = newApplicationModelList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldApplicationModelList[oldItemPosition].appBundleId == newApplicationModelList[newItemPosition].appBundleId
    }
}