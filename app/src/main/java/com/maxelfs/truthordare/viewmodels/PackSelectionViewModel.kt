package com.maxelfs.truthordare.viewmodels

import androidx.lifecycle.*
import com.maxelfs.truthordare.interfaces.*
import com.maxelfs.truthordare.models.ActivityPack
import com.maxelfs.truthordare.models.ActivityPackWithIncludes
import com.maxelfs.truthordare.models.Locale
import com.maxelfs.truthordare.views.packs.PackViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PackSelectionViewModel @Inject constructor(
    private val _packService: ActivityPackService,
    private val _appSettingsService: AppSettingsService
) : ViewModel()  {
    private val _packs = MutableLiveData<List<ActivityPackWithIncludes>>()
    private val _navigateToPlayerSelectionEvent = MutableLiveData<Boolean>()
    private val _navigateToLanguageSelectionEvent = MutableLiveData<Boolean>()
    private val _navigateToRateAppEvent = MutableLiveData<Boolean>()


    val packs: LiveData<List<PackViewModel>> = Transformations.map(_packs) {
        it.map { pack -> PackViewModel(pack, _appSettingsService) }
    }

    val navigateToPlayerSelectionEvent: LiveData<Boolean> = _navigateToPlayerSelectionEvent

    val navigateToLanguageSelectionEvent: LiveData<Boolean> = _navigateToLanguageSelectionEvent

    val navigateToRateAppEvent: LiveData<Boolean> = _navigateToRateAppEvent

    val currentLocale: Locale = _appSettingsService.getCurrentLocale()

    val shouldShowRateApp: Boolean
    get() = !_appSettingsService.didUserGoToAppRateInGooglePlay


    init {
        _packs.value = emptyList()
    }

    fun initializeOrRestore() {
        if (packs.value != null && packs.value!!.isNotEmpty()) {
            return
        }

        refreshPacks()
    }

    fun startGame(pack: ActivityPack) {
        _packService.selectPack(pack)
        _navigateToPlayerSelectionEvent.value = true
    }

    fun onNavigatedToPlayerSelection() {
        _navigateToPlayerSelectionEvent.value = false
    }

    fun navigateToLanguageSelection() {
        _appSettingsService.isSettingLanguageFromPackSelection = true
        _navigateToLanguageSelectionEvent.value = true
    }

    fun onNavigatedToLanguageSelection() {
        _navigateToLanguageSelectionEvent.value = false
    }

    fun navigateToRateApp() {
        _navigateToRateAppEvent.value = true
    }

    fun onNavigatedToRateApp() {
        _navigateToRateAppEvent.value = false
    }


    private fun refreshPacks() {
        viewModelScope.launch {
            refreshPacksAsync()
        }
    }

    private suspend fun refreshPacksAsync() {
        val packs = _packService.getPacksAsync()
        _packs.value = packs.sortedBy { it.pack.sortOrder }
    }
}