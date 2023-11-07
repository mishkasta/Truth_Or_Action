package com.maxelfs.truthordare.views.packs

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.models.ActivityPackIcon

@BindingAdapter("packIcon")
fun setPackIcon(
    imageView: ImageView,
    packIcon: ActivityPackIcon
) {
    val genderImageId = getIconId(packIcon)
    imageView.setImageResource(genderImageId)
}

private fun getIconId(packIcon: ActivityPackIcon) = when (packIcon) {
    ActivityPackIcon.CAKE -> R.drawable.light_18
    ActivityPackIcon.ROCKET -> R.drawable.ic_light_pack
    ActivityPackIcon.PARTY -> R.drawable.party
    ActivityPackIcon.EXPLOSION -> R.drawable.ic_pack_explosion
    ActivityPackIcon.DATE -> R.drawable.ic_pack_date
    ActivityPackIcon.HEART -> R.drawable.ic_pack_heart
}