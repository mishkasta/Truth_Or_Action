package com.maxelfs.truthordare.interfaces

import android.content.Context
import android.view.View
import android.view.ViewGroup

interface TooltipService {
    fun initialize(context: Context, root: ViewGroup)

    fun showTooltip(text: String, anchor: View, position: Position)

    fun hideTooltip()



    @Suppress("unused")
    enum class Position(val value: Int) {
        ABOVE(0),
        BELOW(1),
        LEFT_TO(3),
        RIGHT_TO(4)
    }
}