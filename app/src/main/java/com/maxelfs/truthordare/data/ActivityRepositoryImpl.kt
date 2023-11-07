package com.maxelfs.truthordare.data

import com.maxelfs.truthordare.interfaces.ActivityRepository
import com.maxelfs.truthordare.models.ActivityWithIncludes
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