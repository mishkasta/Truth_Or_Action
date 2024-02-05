package com.maxelfs.truthandaction.services

import com.maxelfs.truthandaction.interfaces.AppSettingsService
import com.maxelfs.truthandaction.interfaces.InsertVariableStrategy
import com.maxelfs.truthandaction.models.ActivityVariable
import com.maxelfs.truthandaction.models.Player

class InsertInternationalizationVariableStrategyImpl constructor(
    private val _appSettingsService : AppSettingsService
) : InsertVariableStrategy {
    override fun insertVariable(text: String, variable: ActivityVariable, player: Player)
    : String {
        val currentLocale = _appSettingsService.getCurrentLocale()
        if (variable.locale!! != currentLocale || variable.gender!! != player.gender) {
            return text
        }

        return text.replace(variable.placeholder!!, variable.localizedValue!!)
    }
}