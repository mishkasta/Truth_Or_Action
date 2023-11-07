package com.maxelfs.truthordare.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.interfaces.RateService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RateAppViewModel @Inject constructor(
    private val _appSettingsService: AppSettingsService,
    private val _rateService: RateService
): ViewModel() {
    private val _navigateBackEvent = MutableLiveData<Boolean>()


    val navigateBackEvent: LiveData<Boolean> = _navigateBackEvent


    fun openGooglePlay() {
        viewModelScope.launch {
            _rateService.rateAppAsync()
            _appSettingsService.didUserGoToAppRateInGooglePlay = true
            close()
        }
    }

    fun close() {
        _navigateBackEvent.value = true
    }

    fun onNavigatedBack() {
        _navigateBackEvent.value = false
    }
}