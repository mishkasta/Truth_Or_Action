package com.maxelfs.truthandaction.services

import com.maxelfs.truthandaction.interfaces.InsertVariableStrategy
import com.maxelfs.truthandaction.models.ActivityVariable
import com.maxelfs.truthandaction.models.Player
import kotlin.random.Random

class InsertRandomNumberVariableStrategyImpl : InsertVariableStrategy {
    override fun insertVariable(text: String, variable: ActivityVariable, player: Player)
    : String {
        val randomNumber = Random.nextInt(
            variable.randomNumberMinValue!!,
            variable.randomNumberMaxValue!!)

        return text.replace(variable.placeholder!!, "$randomNumber")
    }
}