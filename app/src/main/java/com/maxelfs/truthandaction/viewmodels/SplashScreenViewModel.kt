package com.maxelfs.truthandaction.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maxelfs.truthandaction.interfaces.AppSettingsService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val _appSettingsService: AppSettingsService,
    application: Application) : AndroidViewModel(application) {
    private val _navigateToStartEvent = MutableLiveData<Boolean>()
    private val _navigateToLanguageSelectionEvent = MutableLiveData<Boolean>()


    val navigateToStartEvent : LiveData<Boolean> = _navigateToStartEvent

    val navigateToLanguageSelectionEvent : LiveData<Boolean> = _navigateToLanguageSelectionEvent


    fun navigateNext() {
        viewModelScope.launch {
            val shouldShowLanguageSelection = _appSettingsService
                .checkIfShouldShowLanguageSelectionOnLaunch()

            if (shouldShowLanguageSelection) {
                _navigateToLanguageSelectionEvent.value = true
            } else {
                _navigateToStartEvent.value = true
            }
        }
    }

    fun onNavigatedToStart() {
        _navigateToStartEvent.value = false
    }

    fun onNavigatedToLanguageSelection() {
        _navigateToLanguageSelectionEvent.value = false
    }
}