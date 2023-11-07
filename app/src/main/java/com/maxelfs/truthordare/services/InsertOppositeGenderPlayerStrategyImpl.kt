package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.InsertVariableStrategy
import com.maxelfs.truthordare.interfaces.PlayerService
import com.maxelfs.truthordare.models.ActivityVariable
import com.maxelfs.truthordare.models.Gender
import com.maxelfs.truthordare.models.Player

class InsertOppositeGenderPlayerStrategyImpl constructor(
    private val _playerService: PlayerService) : InsertVariableStrategy {
    override fun insertVariable(
        text: String,
        variable: ActivityVariable,
        player: Player): String {
        val oppositeGender = when (player.gender) {
            Gender.NOT_SET -> Gender.NOT_SET
            Gender.MALE -> Gender.FEMALE
            Gender.FEMALE -> Gender.MALE
        }

        var targetPlayers = _playerService.getPlayers()
        if (oppositeGender != Gender.NOT_SET) {
            targetPlayers = targetPlayers.filter { it.gender == oppositeGender }
        }
        val targetPlayer = targetPlayers.shuffled().first()

        return text.replace(variable.placeholder!!, targetPlayer.name)
    }
}