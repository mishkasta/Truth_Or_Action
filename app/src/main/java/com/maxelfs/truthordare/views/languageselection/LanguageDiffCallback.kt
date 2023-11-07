package com.maxelfs.truthordare.views.languageselection

import androidx.recyclerview.widget.DiffUtil

class LanguageDiffCallback : DiffUtil.ItemCallback<LanguageViewModel>() {
    override fun areItemsTheSame(oldItem: LanguageViewModel, newItem: LanguageViewModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: LanguageViewModel,
        newItem: LanguageViewModel): Boolean {
        return oldItem.locale == newItem.locale
               && oldItem.isSelected.value == newItem.isSelected.value
    }
}