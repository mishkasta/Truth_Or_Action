package com.maxelfs.truthordare.views


import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun convertBooleanToVisibility(view: View, isVisible: Boolean) {
    val visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }

    view.visibility = visibility
}