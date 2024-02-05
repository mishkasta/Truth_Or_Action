package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.Player

interface PlayerService {
    fun savePlayers(players: List<Player>)

    fun getNextPlayer(): Player?

    fun getPlayers(): List<Player>
}