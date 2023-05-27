package com.example.news.helpers

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
//   url?.let { view.load(url) }
}