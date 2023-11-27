package com.maxelfs.truthanddare.data

import com.maxelfs.truthanddare.interfaces.ActivityPackRepository
import com.maxelfs.truthanddare.models.ActivityPackWithIncludes
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