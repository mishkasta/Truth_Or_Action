package com.maxelfs.truthordare.views.languageselection

class LanguageClickListener(private val clickListener: (language: LanguageViewModel) -> Unit) {
    fun onClick(locale: LanguageViewModel) = clickListener(locale)
}