package com.maxelfs.truthanddare.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.maxelfs.truthanddare.models.ActivityWithIncludes

@Dao
interface ActivityDao {
    @Transaction
    @Query("SELECT * FROM activities WHERE parentPackId = :packId")
    fun getAllWithIncludes(packId: Int) : List<ActivityWithIncludes>
}