package com.maxelfs.truthordare.services

import android.app.Activity
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.maxelfs.truthordare.interfaces.ActivityProvider
import com.maxelfs.truthordare.interfaces.RateService
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RateServiceImpl @Inject constructor(
    private val _activityProvider: ActivityProvider
) : RateService {
    private val activity: Activity
    get() = _activityProvider.activity


    override suspend fun rateAppAsync() {
        val manager = ReviewManagerFactory.create(activity.applicationContext)
        val reviewInfo = getReviewInfoAsync(manager)
        reviewInfo?.let {
            launchReviewFlowAsync(manager, it)
        }
    }


    private suspend fun getReviewInfoAsync(manager: ReviewManager): ReviewInfo?
        = suspendCoroutine { continuation ->
        val request = manager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(task.result)
            } else {
                continuation.resume(null)
            }
        }
    }

    private suspend fun launchReviewFlowAsync(manager: ReviewManager, reviewInfo: ReviewInfo)
    : Boolean = suspendCoroutine { continuation ->
        val flow = manager.launchReviewFlow(activity, reviewInfo)
        flow.addOnCompleteListener {
            continuation.resume(true)
        }
    }
}