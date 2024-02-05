package com.maxelfs.truthandaction.views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.maxelfs.truthandaction.R


@BindingAdapter("pageIndex", "imagePageIndex")
fun setPage(
    image: ImageView,
    pageIndex: Int,
    imagePageIndex: Int) {
    if (pageIndex == imagePageIndex) {
        image.setImageResource(R.drawable.ic_rectangle)
    } else {
        image.setImageResource(R.drawable.ic_ellipse)
    }
}