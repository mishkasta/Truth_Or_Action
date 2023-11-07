package com.maxelfs.truthordare.services

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.maxelfs.truthordare.R
import com.maxelfs.truthordare.interfaces.ActivityProvider
import com.maxelfs.truthordare.interfaces.AdService
import javax.inject.Inject

class AdServiceImpl @Inject constructor(
    private val _activityProvider: ActivityProvider
) : AdService {
    private var _interstitialAd : InterstitialAd? = null
    private var _isShowingInterstitial = false
    private var _isInterstitialReloadRequested = false

    private val activity: Activity
    get() = _activityProvider.activity


    override fun reloadInterstitial() {
        if (_isShowingInterstitial) {
            _isInterstitialReloadRequested = true

            return
        }
        _isInterstitialReloadRequested = false

        val adRequest = AdRequest.Builder().build()

        val context = activity.applicationContext
        InterstitialAd.load(
            context,
            context.getString(R.string.interstitialId),
            adRequest,
            object: InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    _interstitialAd = ad
                }
            }
        )
    }

    override fun showInterstitial() {
        val interstitialAd = _interstitialAd ?: return

        interstitialAd.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                onInterstitialGone()
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                onInterstitialGone()
            }
        }

        _isShowingInterstitial = true
        interstitialAd.show(activity)
    }


    private fun onInterstitialGone() {
        _isShowingInterstitial = false
        _interstitialAd = null

        if (_isInterstitialReloadRequested) {
            reloadInterstitial()
        }
    }
}