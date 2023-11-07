package com.maxelfs.truthordare.views.packs

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.models.ActivityPackColor

@BindingAdapter("packColor")
fun setPackColor(
    layout: ConstraintLayout,
    packColor: ActivityPackColor
) {
    val colorId = getColorId(packColor)
        //layout.setBackgroundResource(colorId)
}

private fun getColorId(packColor: ActivityPackColor) = when (packColor) {
    ActivityPackColor.BLUE -> R.drawable.background_pack_blue
    ActivityPackColor.GREEN -> R.drawable.background_pack_green
    ActivityPackColor.PURPLE -> R.drawable.background_pack_purple
    ActivityPackColor.BLUE_GRADIENT -> R.drawable.background_pack_blue_gradient
    ActivityPackColor.ORANGE_GRADIENT -> R.drawable.background_pack_orange_gradient
    ActivityPackColor.RED_GRADIENT -> R.drawable.background_pack_red_gradient
}