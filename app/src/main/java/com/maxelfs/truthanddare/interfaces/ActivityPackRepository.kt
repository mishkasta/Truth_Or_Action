package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityPackWithIncludes

interface ActivityPackRepository {
    suspend fun getPacksWithIncludesAsync(type: Int): List<ActivityPackWithIncludes>
}