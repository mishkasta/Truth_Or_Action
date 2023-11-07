package com.maxelfs.truthordare.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "activities",
    foreignKeys = [
        ForeignKey(
            entity = ActivityPack::class,
            parentColumns = ["packId"],
            childColumns = ["parentPackId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            name = "IX_activities_parentPackId",
            unique = false,
            orders = [ Index.Order.ASC ],
            value = [ "parentPackId" ]
        )
    ]
)
data class Activity(
    @PrimaryKey(autoGenerate = true) val activityId: Int,
    val parentPackId: Int,
    val typeId: Int,
    val genderId: Int,
    val text: String?
) {
    val type: ActivityType
    get() {
        return ActivityType.values().single { it.id == typeId }
    }

    val gender: Gender
    get() {
        return Gender.values().single { it.id == genderId }
    }
}
