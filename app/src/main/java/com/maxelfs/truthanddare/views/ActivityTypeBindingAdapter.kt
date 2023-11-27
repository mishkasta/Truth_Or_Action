package com.maxelfs.truthanddare.views

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.maxelfs.truthanddare.R
import com.maxelfs.truthanddare.models.ActivityType

@BindingAdapter("activityType")
fun getActivityTypeText(textView: TextView, activityType: ActivityType?) {
    if (activityType == null) {
        return
    }

    val activityTypeTextId = when (activityType) {
        ActivityType.TRUTH -> R.string.truth
        ActivityType.ACTION -> R.string.action
    }

    textView.setText(activityTypeTextId)
}