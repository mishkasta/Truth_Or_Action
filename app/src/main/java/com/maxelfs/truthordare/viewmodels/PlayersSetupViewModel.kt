package com.maxelfs.truthordare.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.interfaces.PlayerService
import com.maxelfs.truthordare.models.Gender
import com.maxelfs.truthordare.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PlayersSetupViewModel @Inject constructor(
    private val _playerService: PlayerService,
    application: Application)
    : ViewModelBase(application) {
    private val _navigateToGameEvent = MutableLiveData<Boolean>()
    private val _players = MutableLiveData<List<Player>>()
    private val _isEditing = MutableLiveData<Boolean>()
    private val _editingErrorId = MutableLiveData<Int>()

    private var _currentId = 1L

    private var _editingPlayerId: Long = 0
    private val _editingPlayerGender = MutableLiveData<Gender>()


    val navigateToGameEvent: LiveData<Boolean> = _navigateToGameEvent

    val players: LiveData<List<Player>> = _players

    val canStartGame: LiveData<Boolean> = Transformations.map(_players) {
        checkIfCanStart(it)
    }

    val isEditing: LiveData<Boolean> = _isEditing

    var editingPlayerName = MutableLiveData<String>()

    val editingPlayerGender: LiveData<Gender> = _editingPlayerGender

    val editingErrorId: LiveData<Int> = _editingErrorId


    init {
        _players.value = emptyList()
    }


    fun initializeOrRestore() {
        val existingPlayers = _playerService.getPlayers()
        if (existingPlayers.isNotEmpty()) {
            _currentId = existingPlayers.maxOf { it.id } + 1
        }
        val players = _players.value!! + existingPlayers
        updatePlayers(players)
    }

    fun suspend() {
        _playerService.savePlayers(_players.value!!)
        _players.value = emptyList()
    }

    fun startGame() {
        if (!checkIfCanStart(_players.value!!)) {
            return
        }

        _navigateToGameEvent.value = true
    }

    fun onNavigatedToGame() {
        _navigateToGameEvent.value = false
    }

    fun startPlayerCreation() {
        _editingPlayerId = _currentId
        _currentId += 1
        editingPlayerName.value = ""
        _editingPlayerGender.value = Gender.MALE

        _isEditing.value = true
    }

    fun setPlayerGender(gender: Gender) {
        _editingPlayerGender.value = gender
    }

    fun startUpdating(player: Player) {
        _editingPlayerId = player.id
        editingPlayerName.value = player.name
        _editingPlayerGender.value = player.gender

        _isEditing.value = true
    }

    fun cancelEditing() {
        _editingErrorId.value = -1
        _editingPlayerId = 0
        _editingPlayerGender.value = Gender.NOT_SET
        editingPlayerName.value = ""
        _isEditing.value = false
    }

    fun completeEditing() : Boolean {
        val playerName = editingPlayerName.value
        if (playerName.isNullOrBlank()) {
            _editingErrorId.value = R.string.empty_name

            return false
        }
        if (checkIfNameIsNotUnique(playerName, _editingPlayerId)) {
            _editingErrorId.value = R.string.not_unique_name

            return false
        }

        _editingErrorId.value = -1

        var players = _players.value!!
        val editingPlayer = players.singleOrNull {
            it.id == _editingPlayerId
        }
        if (editingPlayer == null) {
            val newPlayer = Player().apply {
                id = _editingPlayerId
                name = editingPlayerName.value!!
                gender = _editingPlayerGender.value!!
            }
            players = players + newPlayer
        } else {
            editingPlayer.name = editingPlayerName.value!!
            editingPlayer.gender = _editingPlayerGender.value!!
        }

        updatePlayers(players)

        _isEditing.value = false
        _editingPlayerId = 0
        _editingPlayerGender.value = Gender.MALE
        editingPlayerName.value = ""

        return true
    }

    fun deletePlayer(player: Player) {
        val newPlayers = _players.value!!
            .filter { it.id != player.id }

        updatePlayers(newPlayers)
    }

    private fun updatePlayers(newPlayers: List<Player>) {
        _players.value = newPlayers
            .map { it.copy() }
            .mapIndexed { i, player ->
                val newPosition = i + 1
                player.apply { position = newPosition
            }
        }
    }

    private fun checkIfCanStart(players: List<Player>): Boolean {
        return players.size > 1
    }

    private fun checkIfNameIsNotUnique(name: String, currentPlayerId: Long) : Boolean {
        return _players.value!!.any {
            it.id != currentPlayerId &&
                    it.name.lowercase(Locale.getDefault()) == name.lowercase(Locale.getDefault())
        }
    }
}