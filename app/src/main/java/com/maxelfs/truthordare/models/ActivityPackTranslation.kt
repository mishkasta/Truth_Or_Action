package com.maxelfs.truthordare.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "packTranslations",
    foreignKeys = [
        ForeignKey(
            entity = ActivityPack::class,
            parentColumns = ["packId"],
            childColumns = ["translatedPackId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            name = "IX_packTranslations_translatedPackId",
            unique = false,
            orders = [ Index.Order.ASC ],
            value = [ "translatedPackId" ]
        )
    ]
)
data class ActivityPackTranslation(
    @PrimaryKey val packTranslationId: Int,
    val translatedPackId: Int,
    val localeId: Int,
    val name: String,
    val description: String
) {
    constructor(name: String, description: String)
            : this(0, 0, Locale.EN.id, name, description)

    val locale: Locale
        get() = Locale.values().single { it.id == localeId }
}