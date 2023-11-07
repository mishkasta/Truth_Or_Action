package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.ActivityWithIncludes

interface ActivityRepository {
    suspend fun getActivitiesWithTranslationsAsync(packId: Int): List<ActivityWithIncludes>
}