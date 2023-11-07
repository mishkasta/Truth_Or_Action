package com.maxelfs.truthordare.views.players

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("position")
fun setPosition(textView: TextView, position: Int) {
    textView.text = "$position."
}