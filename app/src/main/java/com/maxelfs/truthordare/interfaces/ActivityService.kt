package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.ActivityType
import com.maxelfs.truthordare.models.Player

interface ActivityService {
    suspend fun reloadActivitiesAsync() : Boolean

    suspend fun getNextActivityTextAsync(activityType: ActivityType, player: Player) : String?
}