package com.maxelfs.truthordare.interfaces

import com.maxelfs.truthordare.models.ActivityWithIncludes
import com.maxelfs.truthordare.models.Player

interface ActivityTextFormatter {
    fun getFormattedText(activity: ActivityWithIncludes, player: Player) : String?
}