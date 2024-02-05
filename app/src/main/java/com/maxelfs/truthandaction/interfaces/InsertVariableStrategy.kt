package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.ActivityVariable
import com.maxelfs.truthandaction.models.Player

interface InsertVariableStrategy {
    fun insertVariable(text: String, variable: ActivityVariable, player: Player) : String?
}