package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.interfaces.InsertVariableStrategy
import com.maxelfs.truthordare.models.ActivityVariable
import com.maxelfs.truthordare.models.Player

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