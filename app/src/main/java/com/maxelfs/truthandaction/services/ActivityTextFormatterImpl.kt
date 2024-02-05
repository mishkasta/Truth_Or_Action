package com.maxelfs.truthandaction.services

import com.maxelfs.truthandaction.interfaces.ActivityTextFormatter
import com.maxelfs.truthandaction.interfaces.AppSettingsService
import com.maxelfs.truthandaction.interfaces.InsertVariableStrategyProvider
import com.maxelfs.truthandaction.models.ActivityWithIncludes
import com.maxelfs.truthandaction.models.PLACEHOLDER_PATTERN
import com.maxelfs.truthandaction.models.Player
import javax.inject.Inject

class ActivityTextFormatterImpl @Inject constructor(
    private val _appSettingsService : AppSettingsService,
    private val _insertStrategyProvider : InsertVariableStrategyProvider
) : ActivityTextFormatter {
    override fun getFormattedText(activity: ActivityWithIncludes, player: Player) : String? {
        var text = getTranslatedText(activity)

        val variables = activity.variables
        for (v in variables) {
            val insertStrategy = _insertStrategyProvider.provideStrategyFor(v)
            text = insertStrategy.insertVariable(text, v, player) ?: return null
        }

        return Regex(PLACEHOLDER_PATTERN).replace(text, "").trim()
    }


    private fun getTranslatedText(activity: ActivityWithIncludes) : String {
        val currentLocale = _appSettingsService.getCurrentLocale()
        val translatedText = activity.translations
            .singleOrNull {
                    l -> l.localeId == currentLocale.id
            }

        return translatedText?.text ?: activity.activity.text!!
    }
}