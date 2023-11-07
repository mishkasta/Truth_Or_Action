package com.maxelfs.truthordare.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxelfs.truthordare.interfaces.AppSettingsService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val MAX_PAGE_INDEX = 2

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val _appSettingsService: AppSettingsService
) : ViewModel() {
    private val _currentPageIndex = MutableLiveData(0)
    private val _navigateToStartGameEvent = MutableLiveData<Boolean>()


    val currentPageIndex : LiveData<Int> = _currentPageIndex

    val navigateToStartGameEvent : LiveData<Boolean> = _navigateToStartGameEvent


    fun nextPage() {
        val newPageIndex = currentPageIndex.value!! + 1
        setPage(newPageIndex)
    }

    fun setPage(pageIndex : Int) {
        _currentPageIndex.value = pageIndex
        onNextPage()
    }

    fun onNextPage() {
        val pageIndex = currentPageIndex.value!!
        if (pageIndex > MAX_PAGE_INDEX) {
            navigateFromOnboarding()
        }
    }

    fun onNavigatedToStartGame() {
        _navigateToStartGameEvent.value = false
        _appSettingsService.setOnboardingShown()
    }


    private fun navigateFromOnboarding() {
        navigateToStartGame()
    }

    private fun navigateToStartGame() {
        _navigateToStartGameEvent.value = true
    }
}