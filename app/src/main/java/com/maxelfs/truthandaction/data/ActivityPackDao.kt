package com.maxelfs.truthandaction.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.maxelfs.truthandaction.models.ActivityPackWithIncludes

@Dao
interface ActivityPackDao {
    @Transaction
    @Query("SELECT * FROM packs WHERE type = :type")
    fun getAll(type: Int) : List<ActivityPackWithIncludes>


}