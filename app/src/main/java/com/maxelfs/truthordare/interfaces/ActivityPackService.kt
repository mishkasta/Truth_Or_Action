package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.ActivityPack
import com.maxelfs.truthordare.models.ActivityPackWithIncludes

interface ActivityPackService {
    suspend fun getPacksAsync(): List<ActivityPackWithIncludes>

    fun selectPack(pack: ActivityPack)

    fun getSelectedPackId(): Int
}