package com.maxelfs.truthordare.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

const val PLACEHOLDER_PATTERN = "\\[\\[[a-zA-Z]+\\d*\\]\\]"

@Entity(
    tableName = "variables",
    foreignKeys = [
        ForeignKey(
            entity = Activity::class,
            parentColumns = ["activityId"],
            childColumns = ["parentActivityId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            name = "IX_variables_parentActivityId",
            unique = false,
            orders = [ Index.Order.ASC ],
            value = [ "parentActivityId" ]
        )
    ]
)
data class ActivityVariable (
    @PrimaryKey(autoGenerate = true) val variableId: Int,
    val parentActivityId: Int,
    val typeId: Int,
    var placeholder: String?,
    val randomNumberMinValue: Int?,
    var randomNumberMaxValue: Int?,
    val genderId: Int?,
    val localeId: Int?,
    val localizedValue: String?) {

    val type: ActivityVariableType
        get() {
            return ActivityVariableType.values().single { it.id == typeId }
        }

    val locale: Locale?
        get() {
            if (localeId == null) {
                return null
            }

            return Locale.values().single { it.id == localeId }
        }

    val gender: Gender?
        get() {
            if (genderId == null) {
                return null
            }

            return Gender.values().single { it.id == genderId }
        }
}