package com.maxelfs.truthandaction.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxelfs.truthandaction.interfaces.AppSettingsService
import com.maxelfs.truthandaction.models.Locale
import com.maxelfs.truthandaction.views.languageselection.LanguageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val _appSettingsService: AppSettingsService
) : ViewModel() {
    private val _navigateToPackSelectionEvent = MutableLiveData<Boolean>()
    private val _locales = MutableLiveData<List<LanguageViewModel>>()


    val locales: LiveData<List<LanguageViewModel>> = _locales

    val navigateToPackSelectionEvent: LiveData<Boolean> = _navigateToPackSelectionEvent


    init {
        _locales.value = Locale.values()
            .map {
                LanguageViewModel(it, _appSettingsService.getCurrentLocale() == it)
            }
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
        val shouldShowPackSelection = _appSettingsService.isSettingLanguageFromPackSelection

        if (shouldShowPackSelection) {
            _navigateToPackSelectionEvent.value = true
            _appSettingsService.isSettingLanguageFromPackSelection = false
        } else {
            _navigateToPackSelectionEvent.value = true
        }
    }
}