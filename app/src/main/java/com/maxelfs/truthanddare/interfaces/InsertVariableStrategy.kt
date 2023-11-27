package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityVariable
import com.maxelfs.truthanddare.models.Player

interface InsertVariableStrategy {
    fun insertVariable(text: String, variable: ActivityVariable, player: Player) : String?
}