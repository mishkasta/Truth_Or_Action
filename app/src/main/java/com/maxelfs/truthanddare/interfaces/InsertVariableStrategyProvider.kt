package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityVariable

interface InsertVariableStrategyProvider {
    fun provideStrategyFor(variable: ActivityVariable) : InsertVariableStrategy
}