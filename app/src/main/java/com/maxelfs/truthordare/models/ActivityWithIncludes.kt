package com.maxelfs.truthordare.models

import androidx.room.Embedded
import androidx.room.Relation

data class ActivityWithIncludes(
    @Embedded val activity: Activity,
    @Relation(parentColumn = "activityId", entityColumn = "translatedActivityId")
    val translations: List<ActivityTranslation>,
    @Relation(parentColumn = "activityId", entityColumn = "parentActivityId")
    val variables: List<ActivityVariable>
)
