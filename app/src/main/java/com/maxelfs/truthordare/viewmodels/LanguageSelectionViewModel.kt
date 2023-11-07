package com.maxelfs.truthordare.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.models.Locale
import com.maxelfs.truthordare.views.languageselection.LanguageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val _appSettingsService: AppSettingsService
) : ViewModel() {
    private val _navigateToOnboardingEvent = MutableLiveData<Boolean>()
    private val _navigateToPackSelectionEvent = MutableLiveData<Boolean>()
    private val _locales = MutableLiveData<List<LanguageViewModel>>()


    val locales: LiveData<List<LanguageViewModel>> = _locales

    val navigateToOnboardingEvent: LiveData<Boolean> = _navigateToOnboardingEvent

    val navigateToPackSelectionEvent: LiveData<Boolean> = _navigateToPackSelectionEvent


    init {
        _locales.value = Locale.values()
            .map {
                LanguageViewModel(it, _appSettingsService.getCurrentLocale() == it)
            }
    }


    fun onNavigatedToOnboarding() {
        _navigateToOnboardingEvent.value = false
    }

    fun onNavigatedToPackSelection() {
        _navigateToPackSelectionEvent.value = false
    }

    fun selectLanguage(language: LanguageViewModel) {
        language.setIsSelected(true)
        _locales.value!!
            .filter { it.locale != language.locale }
            .forEach { it.setIsSelected(false) }
    }

    fun done() {
        _appSettingsService.setLanguageSelectionShownOnLaunch()
        val selectedLanguage = _locales.value!!.single { it.isSelected.value!! }
        _appSettingsService.setLocale(selectedLanguage.locale)

        val shouldShowOnboarding = _appSettingsService.checkIfShouldShowOnboarding()
        val shouldShowPackSelection = _appSettingsService.isSettingLanguageFromPackSelection

        if (shouldShowPackSelection) {
            _navigateToPackSelectionEvent.value = true
            _appSettingsService.isSettingLanguageFromPackSelection = false
        } else if (shouldShowOnboarding) {
            _navigateToOnboardingEvent.value = true
        } else {
            _navigateToPackSelectionEvent.value = true
        }
    }
}