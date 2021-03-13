package com.warlox.apkextractor.ui.appList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.warlox.apkextractor.R
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.databinding.ViewListApplicationBinding
import com.warlox.apkextractor.ui.appList.ApplicationRecycleViewItemClick
import com.warlox.apkextractor.ui.appList.view_holder.ViewHolder
import com.warlox.apkextractor.util.ApplicationModelDiffUtil

class ApplicationListAdapter(private val listener: ApplicationRecycleViewItemClick) :
        ListAdapter<ApplicationModel, ViewHolder>(ApplicationModelDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate(layoutInflater, R.layout.view_list_application, parent, false) as ViewListApplicationBinding

        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}