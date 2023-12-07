package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityPack
import com.maxelfs.truthanddare.models.ActivityPackWithIncludes

interface ActivityPackService {
    suspend fun getPacksAsync(type:  Int): List<ActivityPackWithIncludes>

    fun selectPack(pack: ActivityPack)

    fun getSelectedPackId(): Int
}