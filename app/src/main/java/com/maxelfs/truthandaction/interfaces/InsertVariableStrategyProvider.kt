package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.ActivityVariable

interface InsertVariableStrategyProvider {
    fun provideStrategyFor(variable: ActivityVariable) : InsertVariableStrategy
}