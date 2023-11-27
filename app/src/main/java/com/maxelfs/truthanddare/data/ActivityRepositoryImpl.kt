package com.maxelfs.truthanddare.data

import com.maxelfs.truthanddare.interfaces.ActivityRepository
import com.maxelfs.truthanddare.models.ActivityWithIncludes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val _activityDao : ActivityDao) : ActivityRepository {
    private val _context = Dispatchers.IO


    override suspend fun getActivitiesWithTranslationsAsync(packId: Int)
    : List<ActivityWithIncludes> {
        val activities = withContext(_context) {
            _activityDao.getAllWithIncludes(packId)
        }

        return activities
    }
}