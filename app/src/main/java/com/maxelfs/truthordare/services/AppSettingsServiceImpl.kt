package com.maxelfs.truthordare.services

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.google.gson.Gson
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.models.Locale
import com.maxelfs.truthordare.models.Player
import javax.inject.Inject

private const val PREFERENCES_FILE_NAME = "com.maxelfs.truthoraction.PREFERENCES"
private const val PREF_IS_ONBOARDING_SHOWN = "PrefIsOnboardingShown"
private const val PREF_IS_LANGUAGE_SELECTION_SHOWN_ON_LAUNCH
    = "PrefIsLanguageSelectionShownOnLaunch"
private const val PREF_LANGUAGE = "PrefLanguage"
private const val PREF_DID_USER_GO_TO_RATE_APP_IN_GOOGLE_PLAY = "PrefDidUserGoToRateAppInGooglePlay"
private const val PREF_CURRENT_PACK_ID = "PrefCurrentPackId"
private const val PREF_PLAYERS = "PrefPlayers"

class AppSettingsServiceImpl @Inject constructor(
    private val _context : Context) : AppSettingsService {
    private var _isSettingLanguageFromPackSelection: Boolean = false
    private var _hasRateAppDialogBeenShown: Boolean = false


    override var isSettingLanguageFromPackSelection: Boolean
        get() = _isSettingLanguageFromPackSelection
        set(value) { _isSettingLanguageFromPackSelection = value }

    override var hasRateAppDialogBeenShown: Boolean
        get() = _hasRateAppDialogBeenShown
        set(value) { _hasRateAppDialogBeenShown = value }

    override var didUserGoToAppRateInGooglePlay: Boolean
        get() = getPreferences().getBoolean(PREF_DID_USER_GO_TO_RATE_APP_IN_GOOGLE_PLAY, false)
        set(value) { setBooleanToSharedPrefs(PREF_DID_USER_GO_TO_RATE_APP_IN_GOOGLE_PLAY, value) }

    override var selectedPackId: Int
        get() = getPreferences().getInt(PREF_CURRENT_PACK_ID, 0)
        set(value) {
            setIntToSharedPrefs(PREF_CURRENT_PACK_ID, value)
        }

    override var players: List<Player>
        get() = getObjectFromSharedPrefs<PlayersCollection>(PREF_PLAYERS)?.players ?: emptyList()
        set(value) {
            setObjectToSharedPrefs(PREF_PLAYERS, PlayersCollection(value))
        }


    override fun checkIfShouldShowOnboarding(): Boolean {
        val prefs = getPreferences()
        val wasOnBoardingShown = prefs.getBoolean(PREF_IS_ONBOARDING_SHOWN, false)

        return !wasOnBoardingShown
    }

    override fun setOnboardingShown() {
        setBooleanToSharedPrefs(PREF_IS_ONBOARDING_SHOWN, true)
    }

    override fun getCurrentLocale(): Locale {
        var currentLocale = getPreferences().getString(PREF_LANGUAGE, "")
        if (currentLocale == "") {
            currentLocale = _context.getString(R.string.current_locale)
            setStringToSharedPrefs(PREF_LANGUAGE, currentLocale)
        }

        return Locale.values().single { l -> l.code == currentLocale }
    }

    override fun setLocale(locale: Locale) {
        val currentLocale = getCurrentLocale()
        if (currentLocale == locale) {
            return
        }

        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(locale.code)
        AppCompatDelegate.setApplicationLocales(appLocale)
        setStringToSharedPrefs(PREF_LANGUAGE, locale.code)
    }

    override fun checkIfShouldShowLanguageSelectionOnLaunch() : Boolean {
        return !getPreferences().getBoolean(PREF_IS_LANGUAGE_SELECTION_SHOWN_ON_LAUNCH, false)
    }

    override fun setLanguageSelectionShownOnLaunch() {
        setBooleanToSharedPrefs(PREF_IS_LANGUAGE_SELECTION_SHOWN_ON_LAUNCH, true)
    }


    private fun setBooleanToSharedPrefs(key: String, value: Boolean)
    {
        val prefs = getPreferences()
        with (prefs.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    private inline fun <reified TObject> getObjectFromSharedPrefs(key: String) : TObject? {
        val serializedValue = getPreferences().getString(key, null) ?: return null
        val gson = Gson()

        return try {
            gson.fromJson(serializedValue, TObject::class.java)
        } catch (ex: Exception) {
            null
        }
    }

    private fun setObjectToSharedPrefs(key: String, value: Any) {
        val gson = Gson()
        val serializedValue = gson.toJson(value)

        setStringToSharedPrefs(key, serializedValue)
    }

    private fun setStringToSharedPrefs(key: String, value: String) {
        val prefs = getPreferences()
        with (prefs.edit()) {
            putString(key, value)
            apply()
        }
    }

    private fun setIntToSharedPrefs(key: String, value: Int) {
        val prefs = getPreferences()
        with (prefs.edit()) {
            putInt(key, value)
            apply()
        }
    }

    private fun getPreferences() : SharedPreferences {
        return _context.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }



    private data class PlayersCollection(
        var players: List<Player>
    )
}