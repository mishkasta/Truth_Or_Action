package com.maxelfs.truthordare.views.languageselection

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maxelfs.truthordare.databinding.LanguageItemFirstBinding
import com.maxelfs.truthordare.views.ViewHolderBase

internal class LanguageFirstItemViewHolder private constructor(
    private val _binding: LanguageItemFirstBinding,
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
            selectLanguageClickListener: LanguageClickListener) : LanguageFirstItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LanguageItemFirstBinding.inflate(inflater, parent, false)

            return LanguageFirstItemViewHolder(binding, selectLanguageClickListener)
        }
    }
}