package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityType
import com.maxelfs.truthanddare.models.Player

interface ActivityService {
    suspend fun reloadActivitiesAsync() : Boolean

    suspend fun getNextActivityTextAsync(activityType: ActivityType, player: Player) : String?
}