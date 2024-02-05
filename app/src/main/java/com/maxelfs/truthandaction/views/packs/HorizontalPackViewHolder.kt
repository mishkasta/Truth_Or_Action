package com.maxelfs.truthandaction.views.packs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maxelfs.truthandaction.databinding.PackAvailableItemHorizontalBinding
import com.maxelfs.truthandaction.views.ViewHolderBase

internal class HorizontalPackViewHolder private constructor(
    private val _binding: PackAvailableItemHorizontalBinding,
    private val _startGameClickListener: PackClickListener
): ViewHolderBase<PackViewModel>(_binding.root) {
    override fun bind(item: PackViewModel, itemsCount: Int) {
        _binding.pack = item
        _binding.startGameClickListener = _startGameClickListener
    }


    companion object {
        fun from(
            parent: ViewGroup,
            startGameClickListener: PackClickListener) : HorizontalPackViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PackAvailableItemHorizontalBinding.inflate(inflater, parent, false)

            return HorizontalPackViewHolder(binding, startGameClickListener)
        }
    }
}