package com.maxelfs.truthanddare.interfaces

import android.app.Activity

interface ActivityProvider {
    val activity: Activity


    fun initialize(activity: Activity)
}