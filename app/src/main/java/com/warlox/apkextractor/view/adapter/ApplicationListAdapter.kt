package com.warlox.apkextractor.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.warlox.apkextractor.R
import com.warlox.apkextractor.data.model.ApplicationModel
import com.warlox.apkextractor.databinding.ViewListApplicationBinding
import com.warlox.apkextractor.util.ApplicationListDiffUtil
import com.warlox.apkextractor.view.callback.ApplicationRecycleViewItemClick

class ApplicationListAdapter(val itemClickListener: ApplicationRecycleViewItemClick): RecyclerView.Adapter<ApplicationListAdapter.ViewHolder>() {

    private val list = mutableListOf<ApplicationModel>()

    fun setApplicationList(list:List<ApplicationModel>){
        val result = DiffUtil.calculateDiff(ApplicationListDiffUtil(this.list, list))
        this.list.clear()
        this.list.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate(layoutInflater, R.layout.view_list_application, parent,false) as ViewListApplicationBinding

        return ViewHolder(binding)

//        val view = layoutInflater.inflate(R.layout.view_list_application, parent,false)
//        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position],itemClickListener)
    }


    inner class ViewHolder(private val _itemView: ViewListApplicationBinding) :RecyclerView.ViewHolder(_itemView.root){
        fun bindData(model: ApplicationModel, itemClickListener:ApplicationRecycleViewItemClick){
            _itemView.applicationModel = model
            _itemView.executePendingBindings()
            _itemView.root.setOnClickListener { itemClickListener.onApplicationListItemClick(model) }
        }
    }


//    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
//        fun bindData(model: ApplicationModel){
//            itemView.appNameView.text = model.appName
//            itemView.appIconView.setImageDrawable(model.icon)
//        }
//    }

}