package com.maxelfs.truthandaction.services

import android.app.Activity
import com.maxelfs.truthandaction.interfaces.ActivityProvider
import javax.inject.Inject

class ActivityProviderImpl @Inject constructor() : ActivityProvider {
    private lateinit var _activity: Activity


    override val activity: Activity
        get() = _activity


    override fun initialize(activity: Activity) {
        _activity = activity
    }
}