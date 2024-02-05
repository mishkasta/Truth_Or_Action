package com.maxelfs.truthandaction.services

import com.maxelfs.truthandaction.interfaces.InsertVariableStrategy
import com.maxelfs.truthandaction.interfaces.PlayerService
import com.maxelfs.truthandaction.models.ActivityVariable
import com.maxelfs.truthandaction.models.Gender
import com.maxelfs.truthandaction.models.Player

class InsertPlayerVariableStrategyImpl constructor(
    private val _playerService: PlayerService
) : InsertVariableStrategy {
    override fun insertVariable(text: String, variable: ActivityVariable, player: Player)
    : String? {
        var players = _playerService.getPlayers().filter { it.id != player.id }
        if (variable.gender != null && variable.gender != Gender.NOT_SET) {
            players = players.filter { it.gender == variable.gender }
        }
        val targetPlayer = players.shuffled().firstOrNull() ?: return null

        return text.replace(variable.placeholder!!, targetPlayer.name)
    }
}