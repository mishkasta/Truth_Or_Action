package com.maxelfs.truthordare.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "packs")
data class ActivityPack(
    @PrimaryKey val packId: Int,
    val isPaid: Boolean,
    val name: String,
    val description: String,
    val iconId: Int,
    val colorId: Int,
    val sortOrder: Int
) {
    val icon: ActivityPackIcon
    get() = ActivityPackIcon.values().single { it.id == iconId }

    val color: ActivityPackColor
    get() = ActivityPackColor.values().single { it.id == colorId }
}