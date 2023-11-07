package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.ActivityPackRepository
import com.maxelfs.truthordare.interfaces.ActivityPackService
import com.maxelfs.truthordare.interfaces.AppSettingsService
import com.maxelfs.truthordare.models.ActivityPack
import com.maxelfs.truthordare.models.ActivityPackWithIncludes
import javax.inject.Inject

class ActivityPackServiceImpl @Inject constructor(
    private val _packRepository: ActivityPackRepository,
    private val _appSettingsService: AppSettingsService
) : ActivityPackService {
    override suspend fun getPacksAsync(): List<ActivityPackWithIncludes> {
        return _packRepository.getPacksWithIncludesAsync()
    }

    override fun selectPack(pack: ActivityPack) {
        _appSettingsService.selectedPackId = pack.packId
    }

    override fun getSelectedPackId(): Int {
        return _appSettingsService.selectedPackId
    }
}