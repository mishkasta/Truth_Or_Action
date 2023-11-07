package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.Locale
import com.maxelfs.truthordare.models.Player

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