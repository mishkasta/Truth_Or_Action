package com.maxelfs.truthordare.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.maxelfs.truthordare.models.ActivityWithIncludes

@Dao
interface ActivityDao {
    @Transaction
    @Query("SELECT * FROM activities WHERE parentPackId = :packId")
    fun getAllWithIncludes(packId: Int) : List<ActivityWithIncludes>
}