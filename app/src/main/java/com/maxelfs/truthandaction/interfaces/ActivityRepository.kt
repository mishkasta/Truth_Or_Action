package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.ActivityWithIncludes

interface ActivityRepository {
    suspend fun getActivitiesWithTranslationsAsync(packId: Int): List<ActivityWithIncludes>
}