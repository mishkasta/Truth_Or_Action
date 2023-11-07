package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.interfaces.PlayerService
import com.maxelfs.truthordare.models.Player
import javax.inject.Inject

class PlayerServiceImpl @Inject constructor(
    private val _appSettingService: AppSettingsService
) : PlayerService {
    private val _playersQueue = ArrayDeque<Player>()


    override fun savePlayers(players: List<Player>) {
        _appSettingService.players = players
        _playersQueue.clear()
        _playersQueue.addAll(_appSettingService.players)
    }

    override fun getNextPlayer(): Player? {
        if (_playersQueue.isEmpty()) {
            _playersQueue.addAll(_appSettingService.players)
        }

        return _playersQueue.removeFirstOrNull()
    }

    override fun getPlayers(): List<Player> = _appSettingService.players
}