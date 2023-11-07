package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.interfaces.InsertVariableStrategy
import com.maxelfs.truthordare.interfaces.InsertVariableStrategyProvider
import com.maxelfs.truthordare.interfaces.PlayerService
import com.maxelfs.truthordare.models.ActivityVariable
import com.maxelfs.truthordare.models.ActivityVariableType
import javax.inject.Inject

class InsertVariableStrategyProviderImpl @Inject constructor(
    private val _playerService: PlayerService,
    private val _appSettingsService: AppSettingsService
) : InsertVariableStrategyProvider {
    override fun provideStrategyFor(variable: ActivityVariable) : InsertVariableStrategy {
        return when (variable.type) {
            ActivityVariableType.PLAYER -> InsertPlayerVariableStrategyImpl(_playerService)
            ActivityVariableType.RANDOM_NUMBER -> InsertRandomNumberVariableStrategyImpl()
            ActivityVariableType.INTERNATIONALIZATION ->
                InsertInternationalizationVariableStrategyImpl(_appSettingsService)
            ActivityVariableType.OPPOSITE_GENDER_PLAYER ->
                InsertOppositeGenderPlayerStrategyImpl(_playerService)
        }
    }
}