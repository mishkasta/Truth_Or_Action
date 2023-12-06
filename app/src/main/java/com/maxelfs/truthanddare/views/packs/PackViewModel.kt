package com.maxelfs.truthanddare.views.packs

import com.maxelfs.truthanddare.interfaces.AppSettingsService
import com.maxelfs.truthanddare.models.ActivityPack
import com.maxelfs.truthanddare.models.ActivityPackTranslation
import com.maxelfs.truthanddare.models.ActivityPackWithIncludes
import com.maxelfs.truthanddare.models.Locale

class PackViewModel constructor(
    private val _pack: ActivityPackWithIncludes,
    private val _appSettingService: AppSettingsService
) {
    val pack: ActivityPack
        get() = _pack.pack

    val isAvailable: Boolean
        get() = !pack.isPaid

    val name: String
        get() = getCurrentLocaleTranslation().name

    val description: String
        get() = getCurrentLocaleTranslation().description

    val type: Int
        get() = getCurrentLocaleTranslation().type


    private fun getCurrentLocaleTranslation(): ActivityPackTranslation {
        val currentLocale = _appSettingService.getCurrentLocale()
        if (currentLocale == Locale.EN) {
            return ActivityPackTranslation(name = pack.name, description = pack.description, type = pack.type)
        }

        return _pack.translations.single { it.locale == currentLocale }
    }
}