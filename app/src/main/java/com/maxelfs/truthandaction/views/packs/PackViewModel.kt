package com.maxelfs.truthandaction.views.packs

import com.maxelfs.truthandaction.interfaces.AppSettingsService
import com.maxelfs.truthandaction.models.ActivityPack
import com.maxelfs.truthandaction.models.ActivityPackTranslation
import com.maxelfs.truthandaction.models.ActivityPackWithIncludes
import com.maxelfs.truthandaction.models.Locale

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