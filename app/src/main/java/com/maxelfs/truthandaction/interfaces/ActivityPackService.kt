package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.ActivityPack
import com.maxelfs.truthandaction.models.ActivityPackWithIncludes

interface ActivityPackService {
    suspend fun getPacksAsync(type:  Int): List<ActivityPackWithIncludes>

    fun selectPack(pack: ActivityPack)

    fun getSelectedPackId(): Int
}