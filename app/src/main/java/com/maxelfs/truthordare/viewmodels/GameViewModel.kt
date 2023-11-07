package com.maxelfs.truthordare.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.interfaces.*
import com.maxelfs.truthordare.models.ActivityType
import com.maxelfs.truthordare.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val PLAYERS_FOR_INTERSTITIAL = 5
private const val ACTIVITY_CHANGES_FOR_INTERSTITIAL = 3

@HiltViewModel
class GameViewModel @Inject constructor(
    private val _playerService: PlayerService,
    private val _activityService: ActivityService,
    private val _adService: AdService,
    private val _appSettingsService: AppSettingsService,
    application: Application
) : ViewModelBase(application) {
    private lateinit var _currentPlayer : Player
    private val _currentPlayerName = MutableLiveData<String>()
    private val _actionText = MutableLiveData<String>()
    private val _isActivitySelected = MutableLiveData<Boolean>()

    private var _currentActivityType = MutableLiveData<ActivityType>()

    private var _playersBeforeInterstitial = PLAYERS_FOR_INTERSTITIAL
    private var _activityChangesBeforeInterstitial = ACTIVITY_CHANGES_FOR_INTERSTITIAL

    private val _navigateToRateAppEvent = MutableLiveData<Boolean>()
    private val _navigateToPackSelectionEvent = MutableLiveData<Boolean>()
    private val _navigateToPlayersSetupEvent = MutableLiveData<Boolean>()


    val currentPlayerName : LiveData<String> = _currentPlayerName

    val actionText : LiveData<String> = _actionText

    val isActivitySelected : LiveData<Boolean> = _isActivitySelected

    val selectedActivityType : LiveData<ActivityType> = _currentActivityType

    val navigateToRateAppEvent: LiveData<Boolean> = _navigateToRateAppEvent

    val navigateToPackSelectionEvent: LiveData<Boolean> = _navigateToPackSelectionEvent

    val navigateToPlayersSetupEvent: LiveData<Boolean> = _navigateToPlayersSetupEvent


    fun initializeOrRestore() {
        _adService.reloadInterstitial()

        viewModelScope.launch {
            if (_activityService.reloadActivitiesAsync()) {
                nextPlayer()
            } else {
                _navigateToPackSelectionEvent.value = true
            }
        }
    }

    fun selectTruth() {
        selectActivity(ActivityType.TRUTH)
    }

    fun selectAction() {
        selectActivity(ActivityType.ACTION)
    }

    fun selectRandom() {
        val randomType = ActivityType.values()
            .toList()
            .shuffled()
            .first()
        selectActivity(randomType)
    }

    fun changeAction() {
        val currentActivityType = _currentActivityType.value ?: return

        setActivityText(currentActivityType)
        _activityChangesBeforeInterstitial -= 1
        showInterstitialIfItsTime()
    }

    fun nextPlayer() {
        val currentPlayer = _playerService.getNextPlayer()
        if (currentPlayer == null) {
            _navigateToPlayersSetupEvent.value = true

            return
        }

        _currentPlayer = currentPlayer
        _currentPlayerName.value = _currentPlayer.name
        _actionText.value = ""
        _isActivitySelected.value = false

        _playersBeforeInterstitial -= 1
        _activityChangesBeforeInterstitial = ACTIVITY_CHANGES_FOR_INTERSTITIAL
        showInterstitialIfItsTime()
    }

    override fun goBack() {
        if (_appSettingsService.didUserGoToAppRateInGooglePlay
            || _appSettingsService.hasRateAppDialogBeenShown) {
            super.goBack()
        } else {
            _navigateToRateAppEvent.value = true
            _appSettingsService.hasRateAppDialogBeenShown = true
        }
    }

    fun onNavigatedToRateApp() {
        _navigateToRateAppEvent.value = false
    }

    fun onNavigatedToPackSelection() {
        _navigateToPackSelectionEvent.value = false
    }

    fun onNavigatedToPlayersSetup() {
        _navigateToPlayersSetupEvent.value = false
    }


    private fun showInterstitialIfItsTime() {
        if (_activityChangesBeforeInterstitial > 0 && _playersBeforeInterstitial > 0) {
            return
        }

        _adService.showInterstitial()

        _playersBeforeInterstitial = PLAYERS_FOR_INTERSTITIAL
        _activityChangesBeforeInterstitial = ACTIVITY_CHANGES_FOR_INTERSTITIAL

        _adService.reloadInterstitial()
    }

    private fun selectActivity(type: ActivityType) {
        _isActivitySelected.value = true
        _currentActivityType.value = type

        setActivityText(type)
    }

    private fun setActivityText(activityType: ActivityType) {
        viewModelScope.launch {
            val text = _activityService.getNextActivityTextAsync(activityType, _currentPlayer)
            _actionText.value = text
                ?: getApplication<Application>().getString(R.string.there_is_no_activity_for_you)
        }
    }
}