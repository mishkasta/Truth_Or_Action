package com.maxelfs.truthandaction.views.languageselection

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maxelfs.truthandaction.databinding.LanguageItemBinding
import com.maxelfs.truthandaction.views.ViewHolderBase

internal class LanguageItemViewHolder private constructor(
    private val _binding: LanguageItemBinding,
    private val _selectLanguageClickListener: LanguageClickListener
) : ViewHolderBase<LanguageViewModel>(_binding.root) {
    override fun bind(item: LanguageViewModel, itemsCount: Int) {
        _binding.locale = item
        _binding.selectClickListener = _selectLanguageClickListener

        _binding.executePendingBindings()
    }


    companion object {
        fun from(
            parent: ViewGroup,
            selectLanguageClickListener: LanguageClickListener) : LanguageItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LanguageItemBinding.inflate(inflater, parent, false)

            return LanguageItemViewHolder(binding, selectLanguageClickListener)
        }
    }
}