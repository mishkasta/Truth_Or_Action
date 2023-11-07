package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.ActivityVariable
import com.maxelfs.truthordare.models.Player

interface InsertVariableStrategy {
    fun insertVariable(text: String, variable: ActivityVariable, player: Player) : String?
}