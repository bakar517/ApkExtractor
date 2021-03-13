package com.warlox.apkextractor.ui.appList.view_holder

import androidx.recyclerview.widget.RecyclerView
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.databinding.ViewListApplicationBinding
import com.warlox.apkextractor.ui.appList.ApplicationRecycleViewItemClick

class ViewHolder(private val _itemView: ViewListApplicationBinding,
                 private val listener: ApplicationRecycleViewItemClick) :
        RecyclerView.ViewHolder(_itemView.root) {

    fun bind(model: ApplicationModel) {
        _itemView.applicationModel = model
        _itemView.listener = listener
        _itemView.executePendingBindings()
    }
}