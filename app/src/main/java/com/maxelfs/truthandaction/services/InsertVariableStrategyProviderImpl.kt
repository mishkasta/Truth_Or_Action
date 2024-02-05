package com.maxelfs.truthandaction.services

import com.maxelfs.truthandaction.interfaces.AppSettingsService
import com.maxelfs.truthandaction.interfaces.InsertVariableStrategy
import com.maxelfs.truthandaction.interfaces.InsertVariableStrategyProvider
import com.maxelfs.truthandaction.interfaces.PlayerService
import com.maxelfs.truthandaction.models.ActivityVariable
import com.maxelfs.truthandaction.models.ActivityVariableType
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