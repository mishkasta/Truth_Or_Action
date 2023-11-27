package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.Player

interface PlayerService {
    fun savePlayers(players: List<Player>)

    fun getNextPlayer(): Player?

    fun getPlayers(): List<Player>
}