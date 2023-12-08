package com.maxelfs.truthanddare.views.packs

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.maxelfs.truthanddare.R
import com.maxelfs.truthanddare.models.ActivityPackIcon

@BindingAdapter("packIcon")
fun setPackIcon(
    imageView: ImageView,
    packIcon: ActivityPackIcon
) {
    val genderImageId = getIconId(packIcon)
    imageView.setImageResource(genderImageId)
}

private fun getIconId(packIcon: ActivityPackIcon) = when (packIcon) {
    ActivityPackIcon.CAKE -> R.drawable.pack_3
    ActivityPackIcon.ROCKET -> R.drawable.pack_1
    ActivityPackIcon.PARTY -> R.drawable.pack_2
    ActivityPackIcon.EXPLOSION -> R.drawable.logo_pack_easy
    ActivityPackIcon.DATE -> R.drawable.logo_pack_fun
    ActivityPackIcon.HEART -> R.drawable.ic_pack_heart
}