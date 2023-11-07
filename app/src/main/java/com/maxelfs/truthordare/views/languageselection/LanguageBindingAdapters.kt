package com.maxelfs.truthordare.views.languageselection

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.models.Locale
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("localeToName")
fun setLanguageName(textView: TextView, localeToName: Locale) {
    val nameId = when (localeToName) {
        Locale.EN -> R.string.english_language
        Locale.RU -> R.string.russian_language
    }

    textView.setText(nameId)
}

@BindingAdapter("localeToCode")
fun setLanguageCode(textView: CircleImageView, localeToCode: Locale) {
    val codeId = when (localeToCode) {
        Locale.EN -> R.drawable.en_img
        Locale.RU -> R.drawable.rus_img
    }

    textView.setImageResource(codeId)
}