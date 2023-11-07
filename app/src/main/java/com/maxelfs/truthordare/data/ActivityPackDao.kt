package com.maxelfs.truthordare.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.maxelfs.truthordare.models.ActivityPackWithIncludes

@Dao
interface ActivityPackDao {
    @Transaction
    @Query("SELECT * FROM packs")
    fun getAll() : List<ActivityPackWithIncludes>
}