package com.maxelfs.truthanddare.interfaces

import com.maxelfs.truthanddare.models.ActivityWithIncludes
import com.maxelfs.truthanddare.models.Player

interface ActivityTextFormatter {
    fun getFormattedText(activity: ActivityWithIncludes, player: Player) : String?
}