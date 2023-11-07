package com.maxelfs.truthordare.views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.maxelfs.truthordare.R


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