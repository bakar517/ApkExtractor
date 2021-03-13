package com.warlox.apkextractor.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:bindRecyclerView")
fun bindRecyclerView(recyclerView: RecyclerView, itemSpace: Float) {
    if (recyclerView.layoutManager == null) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }
    val itemDecoration = RecyclerViewItemDecoration(itemSpace.toInt())
    recyclerView.addItemDecoration(itemDecoration)
}
