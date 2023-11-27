package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.Locale
import com.maxelfs.truthanddare.models.Player

interface AppSettingsService {
    var isSettingLanguageFromPackSelection: Boolean

    var hasRateAppDialogBeenShown: Boolean

    var didUserGoToAppRateInGooglePlay: Boolean

    var selectedPackId: Int

    var players: List<Player>


    fun checkIfShouldShowOnboarding() : Boolean

    fun setOnboardingShown()

    fun getCurrentLocale() : Locale

    fun setLocale(locale: Locale)

    fun checkIfShouldShowLanguageSelectionOnLaunch() : Boolean

    fun setLanguageSelectionShownOnLaunch()
}