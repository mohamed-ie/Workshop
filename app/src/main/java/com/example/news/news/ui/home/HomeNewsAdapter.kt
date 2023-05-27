package com.example.news.news.ui.home

import com.example.news.R
import com.example.news.base.BaseAdapter
import com.example.news.databinding.NewItemRowBinding
import com.example.news.news.model.source.local.entitiy.Article

class HomeNewsAdapter (private val list: List<Article>): BaseAdapter<NewItemRowBinding,Article>(R.layout.new_item_row, list
) {
    override fun onBindViewHolder(holder: ViewHolder<NewItemRowBinding>, position: Int) {
        holder.binding.article = list[position]
    }
}