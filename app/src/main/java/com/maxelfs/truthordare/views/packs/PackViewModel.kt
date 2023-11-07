package com.maxelfs.truthordare.views.packs

import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.models.ActivityPack
import com.maxelfs.truthordare.models.ActivityPackTranslation
import com.maxelfs.truthordare.models.ActivityPackWithIncludes
import com.maxelfs.truthordare.models.Locale

class PackViewModel constructor(
    private val _pack: ActivityPackWithIncludes,
    private val _appSettingService: AppSettingsService) {
    val pack: ActivityPack
    get() = _pack.pack

    val isAvailable: Boolean
        get() = !pack.isPaid

    val name: String
    get() = getCurrentLocaleTranslation().name

    val description: String
    get() = getCurrentLocaleTranslation().description


    private fun getCurrentLocaleTranslation() : ActivityPackTranslation {
        val currentLocale = _appSettingService.getCurrentLocale()
        if (currentLocale == Locale.EN) {
            return ActivityPackTranslation(name = pack.name, description = pack.description)
        }

        return _pack.translations.single { it.locale == currentLocale }
    }
}