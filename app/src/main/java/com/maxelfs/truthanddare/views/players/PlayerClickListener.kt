package com.maxelfs.truthanddare.views.players

import com.maxelfs.truthanddare.models.Player

class PlayerClickListener(private val clickListener: (player: Player) -> Unit) {
    fun onClick(player: Player) = clickListener(player)
}