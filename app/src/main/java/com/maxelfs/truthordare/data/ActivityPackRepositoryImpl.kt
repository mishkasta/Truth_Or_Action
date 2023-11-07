package com.maxelfs.truthordare.data

import com.maxelfs.truthordare.interfaces.ActivityPackRepository
import com.maxelfs.truthordare.models.ActivityPackWithIncludes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActivityPackRepositoryImpl @Inject constructor(
    private val _activityPackDao : ActivityPackDao) : ActivityPackRepository {
    private val _context = Dispatchers.IO
    override suspend fun getPacksWithIncludesAsync(): List<ActivityPackWithIncludes> {
        return withContext(_context) {
            _activityPackDao.getAll()
        }
    }
}