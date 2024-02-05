package com.maxelfs.truthandaction.views.languageselection

class LanguageClickListener(private val clickListener: (language: LanguageViewModel) -> Unit) {
    fun onClick(locale: LanguageViewModel) = clickListener(locale)
}