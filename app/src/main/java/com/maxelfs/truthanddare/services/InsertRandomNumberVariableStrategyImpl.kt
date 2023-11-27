package com.maxelfs.truthanddare.services

import com.maxelfs.truthanddare.interfaces.InsertVariableStrategy
import com.maxelfs.truthanddare.models.ActivityVariable
import com.maxelfs.truthanddare.models.Player
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