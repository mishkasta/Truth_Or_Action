package com.maxelfs.truthordare.views

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.models.ActivityType

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