package com.maxelfs.truthordare.views.packs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.maxelfs.truthordare.databinding.PackAvailableItemBinding
import com.maxelfs.truthordare.views.ViewHolderBase

internal class AvailablePackViewHolder private constructor(
    private val _binding: PackAvailableItemBinding,
    private val _startGameClickListener: PackClickListener
): ViewHolderBase<PackViewModel>(_binding.root) {
    override fun bind(item: PackViewModel, itemsCount: Int) {
        _binding.pack = item
        _binding.startGameClickListener = _startGameClickListener
    }


    companion object {
        fun from(
            parent: ViewGroup,
            startGameClickListener: PackClickListener) : AvailablePackViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PackAvailableItemBinding.inflate(inflater, parent, false)

            return AvailablePackViewHolder(binding, startGameClickListener)
        }
    }
}