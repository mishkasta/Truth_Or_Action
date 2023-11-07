package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.InsertVariableStrategy
import com.maxelfs.truthordare.models.ActivityVariable
import com.maxelfs.truthordare.models.Player
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