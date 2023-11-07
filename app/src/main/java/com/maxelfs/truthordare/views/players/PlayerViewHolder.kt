package com.maxelfs.truthordare.views.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maxelfs.truthordare.databinding.PlayerItemBinding
import com.maxelfs.truthordare.models.Player
import com.maxelfs.truthordare.views.ViewHolderBase

internal class PlayerViewHolder private constructor (
    private val _binding : PlayerItemBinding,
    private val _updateClickListener : PlayerClickListener,
    private val _deleteClickListener : PlayerClickListener
) : ViewHolderBase<Player>(_binding.root) {
    override fun bind(item : Player, itemsCount: Int) {
        _binding.player = item
        _binding.updateClickListener = _updateClickListener
        _binding.deleteClickListener = _deleteClickListener

        _binding.deleteButton.visibility = if (itemsCount > 1) {
            View.VISIBLE
        } else {
            View.GONE
        }

        _binding.executePendingBindings()
    }


    companion object {
        fun from(
            parent: ViewGroup,
            updateNameClickListener: PlayerClickListener,
            deleteClickListener: PlayerClickListener) : PlayerViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PlayerItemBinding.inflate(inflater, parent, false)

            return PlayerViewHolder(
                binding,
                updateNameClickListener,
                deleteClickListener)
        }
    }
}