package com.maxelfs.truthanddare.services

import com.maxelfs.truthanddare.interfaces.AppSettingsService
import com.maxelfs.truthanddare.interfaces.InsertVariableStrategy
import com.maxelfs.truthanddare.interfaces.InsertVariableStrategyProvider
import com.maxelfs.truthanddare.interfaces.PlayerService
import com.maxelfs.truthanddare.models.ActivityVariable
import com.maxelfs.truthanddare.models.ActivityVariableType
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