package com.maxelfs.truthanddare.services

import com.maxelfs.truthanddare.interfaces.AppSettingsService
import com.maxelfs.truthanddare.interfaces.PlayerService
import com.maxelfs.truthanddare.models.Player
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