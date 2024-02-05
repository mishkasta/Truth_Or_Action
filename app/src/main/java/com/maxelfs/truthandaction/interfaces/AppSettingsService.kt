package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.Locale
import com.maxelfs.truthandaction.models.Player

interface AppSettingsService {
    var isSettingLanguageFromPackSelection: Boolean

    var hasRateAppDialogBeenShown: Boolean

    var didUserGoToAppRateInGooglePlay: Boolean

    var selectedPackId: Int

    var players: List<Player>


    fun getCurrentLocale() : Locale

    fun setLocale(locale: Locale)

    fun checkIfShouldShowLanguageSelectionOnLaunch() : Boolean

    fun setLanguageSelectionShownOnLaunch()
}