package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityPack
import com.maxelfs.truthanddare.models.ActivityPackWithIncludes

interface ActivityPackService {
    suspend fun getPacksAsync(): List<ActivityPackWithIncludes>

    fun selectPack(pack: ActivityPack)

    fun getSelectedPackId(): Int
}