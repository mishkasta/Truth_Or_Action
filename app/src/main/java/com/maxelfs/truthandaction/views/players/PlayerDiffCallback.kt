package com.maxelfs.truthandaction.views.players

import androidx.recyclerview.widget.DiffUtil
import com.maxelfs.truthandaction.models.Player

internal class PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}