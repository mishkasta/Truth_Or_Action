package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.ActivityVariable

interface InsertVariableStrategyProvider {
    fun provideStrategyFor(variable: ActivityVariable) : InsertVariableStrategy
}