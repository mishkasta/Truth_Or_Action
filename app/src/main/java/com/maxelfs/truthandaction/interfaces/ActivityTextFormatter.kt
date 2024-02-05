package com.maxelfs.truthandaction.interfaces

import com.maxelfs.truthandaction.models.ActivityWithIncludes
import com.maxelfs.truthandaction.models.Player

interface ActivityTextFormatter {
    fun getFormattedText(activity: ActivityWithIncludes, player: Player) : String?
}