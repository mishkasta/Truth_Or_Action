package com.maxelfs.truthandaction.data

import com.maxelfs.truthandaction.interfaces.ActivityPackRepository
import com.maxelfs.truthandaction.models.ActivityPackWithIncludes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActivityPackRepositoryImpl @Inject constructor(
    private val _activityPackDao : ActivityPackDao) : ActivityPackRepository {
    private val _context = Dispatchers.IO
    override suspend fun getPacksWithIncludesAsync(type: Int): List<ActivityPackWithIncludes> {
        return withContext(_context) {
            _activityPackDao.getAll(type)
        }
    }


}