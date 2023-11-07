package com.maxelfs.truthordare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.google.android.gms.ads.MobileAds
import com.maxelfs.truthordare.interfaces.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var activityProvider: ActivityProvider
    @Inject lateinit var tooltipService: TooltipService


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TruthOrAction)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        MobileAds.initialize(this)
        activityProvider.initialize(this)

        tooltipService.initialize(applicationContext, findViewById(R.id.rootView))
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            tooltipService.hideTooltip()
        }

        return super.dispatchTouchEvent(ev)
    }
}