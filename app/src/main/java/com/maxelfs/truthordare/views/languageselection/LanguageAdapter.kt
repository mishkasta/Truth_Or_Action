package com.maxelfs.truthordare.views.languageselection

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxelfs.truthordare.views.ViewHolderBase

private const val FIRST_ITEM = 1
private const val ITEM = 2

@Suppress("UNCHECKED_CAST")
class LanguageAdapter(
    private val _selectLanguageClickListener: LanguageClickListener
) : ListAdapter<LanguageViewModel, RecyclerView.ViewHolder>(LanguageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            FIRST_ITEM -> LanguageFirstItemViewHolder.from(
                parent,
                _selectLanguageClickListener)
            ITEM -> LanguageItemViewHolder.from(
                parent,
                _selectLanguageClickListener)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = holder as ViewHolderBase<LanguageViewModel>
        val locale = getItem(position)
        item.bind(locale, itemCount)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return FIRST_ITEM
        }

        return ITEM
    }
}