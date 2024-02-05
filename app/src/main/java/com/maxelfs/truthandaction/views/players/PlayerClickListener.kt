package com.maxelfs.truthandaction.views.players

import com.maxelfs.truthandaction.models.Player

class PlayerClickListener(private val clickListener: (player: Player) -> Unit) {
    fun onClick(player: Player) = clickListener(player)
}