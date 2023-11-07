package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.Player

interface PlayerService {
    fun savePlayers(players: List<Player>)

    fun getNextPlayer(): Player?

    fun getPlayers(): List<Player>
}