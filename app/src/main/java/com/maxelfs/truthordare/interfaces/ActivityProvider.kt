package com.maxelfs.truthordare.interfaces

import android.app.Activity

interface ActivityProvider {
    val activity: Activity


    fun initialize(activity: Activity)
}