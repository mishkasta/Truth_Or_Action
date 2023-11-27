package com.maxelfs.truthanddare.services

import com.maxelfs.truthanddare.interfaces.AppSettingsService
import com.maxelfs.truthanddare.interfaces.InsertVariableStrategy
import com.maxelfs.truthanddare.models.ActivityVariable
import com.maxelfs.truthanddare.models.Player

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