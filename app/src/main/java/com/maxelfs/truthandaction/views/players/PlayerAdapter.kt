package com.maxelfs.truthandaction.views.players

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxelfs.truthandaction.models.Player
import com.maxelfs.truthandaction.views.ViewHolderBase

@Suppress("UNCHECKED_CAST")
class PlayerAdapter(
    private val _startEditingClickListener: PlayerClickListener,
    private val _deleteClickListener: PlayerClickListener
    ) : ListAdapter<Player, RecyclerView.ViewHolder>(PlayerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayerViewHolder.from(
            parent,
            _startEditingClickListener,
            _deleteClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val playerItem = holder as ViewHolderBase<Player>
        val player = getItem(position)

        playerItem.bind(player, itemCount)
    }
}