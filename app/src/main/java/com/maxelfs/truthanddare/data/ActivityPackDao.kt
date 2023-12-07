package com.maxelfs.truthanddare.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.maxelfs.truthanddare.models.ActivityPackWithIncludes

@Dao
interface ActivityPackDao {
    @Transaction
    @Query("SELECT * FROM packs WHERE type = :type")
    fun getAll(type: Int) : List<ActivityPackWithIncludes>


}