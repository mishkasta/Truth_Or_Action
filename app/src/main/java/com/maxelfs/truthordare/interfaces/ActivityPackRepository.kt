package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.ActivityPackWithIncludes

interface ActivityPackRepository {
    suspend fun getPacksWithIncludesAsync(): List<ActivityPackWithIncludes>
}