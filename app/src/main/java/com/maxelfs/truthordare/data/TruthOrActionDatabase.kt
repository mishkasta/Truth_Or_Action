package com.maxelfs.truthordare.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxelfs.truthordare.models.*

@Database(
    entities = [
        ActivityPack::class,
        ActivityPackTranslation::class,
        Activity::class,
        ActivityTranslation::class,
        ActivityVariable::class
    ],
    version = 1)
abstract class TruthOrActionDatabase : RoomDatabase() {
    abstract val activityDao: ActivityDao

    abstract val packDao: ActivityPackDao
}