package com.maxelfs.truthordare.views.players

import com.maxelfs.truthordare.models.Player

class PlayerClickListener(private val clickListener: (player: Player) -> Unit) {
    fun onClick(player: Player) = clickListener(player)
}