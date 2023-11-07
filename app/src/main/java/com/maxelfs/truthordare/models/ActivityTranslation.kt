package com.maxelfs.truthordare.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import androidx.room.Index.Order.ASC
import androidx.room.PrimaryKey

@Entity(
    tableName = "activityTranslations",
    foreignKeys = [
        ForeignKey(
            entity = Activity::class,
            parentColumns = ["activityId"],
            childColumns = ["translatedActivityId"],
            onDelete = CASCADE
        )
    ],
    indices = [
        Index(
            name = "IX_activityTranslations_translatedActivityId",
            unique = false,
            orders = [ ASC ],
            value = [ "translatedActivityId" ]
        )
    ]
)
data class ActivityTranslation(
    @PrimaryKey(autoGenerate = true) val translationId: Int,
    val translatedActivityId: Int,
    val localeId: Int,
    val text: String
)