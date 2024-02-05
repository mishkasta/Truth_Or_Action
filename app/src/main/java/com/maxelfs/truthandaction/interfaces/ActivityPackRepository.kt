package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.ActivityPackWithIncludes

interface ActivityPackRepository {
    suspend fun getPacksWithIncludesAsync(type: Int): List<ActivityPackWithIncludes>
}