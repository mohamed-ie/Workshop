package com.example.news.helpers

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.example.news.R

@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
    view.load(url) {
        crossfade(true)
        placeholder(R.drawable.news_placeholder)
    }
}