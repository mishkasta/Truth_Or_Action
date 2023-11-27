package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityWithIncludes

interface ActivityRepository {
    suspend fun getActivitiesWithTranslationsAsync(packId: Int): List<ActivityWithIncludes>
}