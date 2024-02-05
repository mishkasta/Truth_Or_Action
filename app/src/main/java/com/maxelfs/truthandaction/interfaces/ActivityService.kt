package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.ActivityType
import com.maxelfs.truthandaction.models.Player

interface ActivityService {
    suspend fun reloadActivitiesAsync() : Boolean

    suspend fun getNextActivityTextAsync(activityType: ActivityType, player: Player) : String?
}