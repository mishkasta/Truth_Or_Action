package com.maxelfs.truthandaction.services

import com.maxelfs.truthandaction.interfaces.ActivityPackRepository
import com.maxelfs.truthandaction.interfaces.ActivityPackService
import com.maxelfs.truthandaction.interfaces.AppSettingsService
import com.maxelfs.truthandaction.models.ActivityPack
import com.maxelfs.truthandaction.models.ActivityPackWithIncludes
import javax.inject.Inject

class ActivityPackServiceImpl @Inject constructor(
    private val _packRepository: ActivityPackRepository,
    private val _appSettingsService: AppSettingsService
) : ActivityPackService {
    override suspend fun getPacksAsync(type:  Int): List<ActivityPackWithIncludes> {
        return _packRepository.getPacksWithIncludesAsync(type)
    }

    override fun selectPack(pack: ActivityPack) {
        _appSettingsService.selectedPackId = pack.packId
    }

    override fun getSelectedPackId(): Int {
        return _appSettingsService.selectedPackId
    }
}