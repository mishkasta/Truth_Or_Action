package com.maxelfs.truthanddare.services

import com.maxelfs.truthanddare.interfaces.ActivityPackRepository
import com.maxelfs.truthanddare.interfaces.ActivityPackService
import com.maxelfs.truthanddare.interfaces.AppSettingsService
import com.maxelfs.truthanddare.models.ActivityPack
import com.maxelfs.truthanddare.models.ActivityPackWithIncludes
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