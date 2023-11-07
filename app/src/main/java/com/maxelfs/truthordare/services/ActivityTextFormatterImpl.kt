package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.ActivityTextFormatter
import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.interfaces.InsertVariableStrategyProvider
import com.maxelfs.truthordare.models.ActivityWithIncludes
import com.maxelfs.truthordare.models.PLACEHOLDER_PATTERN
import com.maxelfs.truthordare.models.Player
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