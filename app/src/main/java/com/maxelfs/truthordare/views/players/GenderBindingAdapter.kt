package com.maxelfs.truthordare.views.players

import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.models.Gender

@BindingAdapter(value = [ "targetGender", "matchToGender", "matchBackground", "defaultBackground" ])
fun setClickableTextBackground(
    textView: TextView,
    targetGender: Gender?,
    matchToGender: Gender,
    matchBackground: Drawable,
    defaultBackground: Drawable
) {
    val resultBackground = if (targetGender == matchToGender) {
        matchBackground
    } else {
        defaultBackground
    }

    textView.background = resultBackground
}

@BindingAdapter("gender")
fun setPlayerGender(
    button: Button,
    newGender: Gender
) {
    val genderTextId = getGenderText(newGender)
    val tag = button.tag as Int?
    if (tag != genderTextId) {
        button.setText(genderTextId)
        val background = getBackgroundId(newGender)
        button.setBackgroundResource(background)
        button.tag = genderTextId
    }
}

@BindingAdapter("genderAttrChanged")
fun toggleGender(
    button: Button,
    attrChange: InverseBindingListener?) {
    if (button.hasOnClickListeners()){
        button.setOnClickListener(null)
    }

    button.setOnClickListener{
        attrChange?.onChange()
    }
}

@InverseBindingAdapter(attribute = "gender")
fun getPlayerGender(
    button: Button) : Gender {
    val tag = button.tag as Int
    val newGender = when (getGender(tag)) {
        Gender.NOT_SET -> Gender.FEMALE
        Gender.MALE -> Gender.FEMALE
        Gender.FEMALE -> Gender.MALE
    }

    val newGenderText = getGenderText(newGender)
    button.setText(newGenderText)
    val background = getBackgroundId(newGender)
    button.setBackgroundResource(background)
    button.tag = newGenderText

    return newGender
}

private fun getGenderText(gender: Gender) : Int = when (gender) {
    Gender.MALE -> R.string.gender_male_short
    Gender.FEMALE -> R.string.gender_female_short
    Gender.NOT_SET -> R.string.gender_not_set_short
}

private fun getGender(displayGender: Int) : Gender = when (displayGender) {
    R.string.gender_not_set_short -> Gender.NOT_SET
    R.string.gender_male_short -> Gender.MALE
    R.string.gender_female_short -> Gender.FEMALE
    else -> Gender.NOT_SET
}

private fun getBackgroundId(gender: Gender) : Int = when (gender) {
    Gender.NOT_SET -> R.drawable.background_gender_default_round
    Gender.MALE -> R.drawable.background_gender_male_round
    Gender.FEMALE -> R.drawable.background_gender_female_round
}