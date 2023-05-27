package com.example.news.helpers

import android.view.View
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("android:error")
fun setError(
    textInputLayout: TextInputLayout,
    @StringRes error: Int?
) {
    textInputLayout.error = if(error == null) null else textInputLayout.resources.getString(error)
}