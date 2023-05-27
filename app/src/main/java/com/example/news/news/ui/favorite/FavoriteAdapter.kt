package com.example.news.news.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.news.R
import com.example.news.databinding.RecyclerViewFavoriteItemBinding
import com.example.news.news.model.source.local.entitiy.Article

class FavoriteAdapter(
    private val deleteArticle: (Article) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    var articles: List<Article> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(val binding: RecyclerViewFavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerViewFavoriteItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_favorite_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.article = article
        holder.binding.imageButton.setOnClickListener { deleteArticle(article) }
    }
}