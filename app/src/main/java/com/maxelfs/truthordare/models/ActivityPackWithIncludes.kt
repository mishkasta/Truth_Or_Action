package com.maxelfs.truthordare.models

import androidx.room.Embedded
import androidx.room.Relation

data class ActivityPackWithIncludes (
    @Embedded
    val pack: ActivityPack,
    @Relation(parentColumn = "packId", entityColumn = "translatedPackId")
    val translations: List<ActivityPackTranslation>
)