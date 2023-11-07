package com.maxelfs.truthordare.views.packs

import androidx.recyclerview.widget.DiffUtil

class PackDiffCallback : DiffUtil.ItemCallback<PackViewModel>() {
    override fun areItemsTheSame(oldItem: PackViewModel, newItem: PackViewModel): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PackViewModel, newItem: PackViewModel): Boolean {
        return oldItem.pack.packId == newItem.pack.packId
    }
}