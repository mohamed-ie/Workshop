package com.example.news.news.ui.home

import com.example.news.R
import com.example.news.base.BaseAdapter
import com.example.news.databinding.NewItemRowBinding

class HomeNewsAdapter : BaseAdapter<NewItemRowBinding,String>(R.layout.new_item_row, mutableListOf()
) {
    override fun onBindViewHolder(holder: ViewHolder<NewItemRowBinding>, position: Int) {
        holder.binding
    }
}