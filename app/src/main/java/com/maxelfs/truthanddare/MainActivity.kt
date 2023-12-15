package com.maxelfs.truthanddare

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import com.maxelfs.truthanddare.interfaces.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject
import android.util.DisplayMetrics




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

        /*var currentLocale = getSharedPreferences("com.maxelfs.truthoraction.PREFERENCES", Context.MODE_PRIVATE).getString("PrefLanguage", "");
        if (currentLocale == "") {
            val configuration: Configuration = resources.configuration
            val locale = Locale("en")
            Locale.setDefault(locale)
            configuration.setLocale(locale)
            val dm: DisplayMetrics = resources.displayMetrics
            resources.updateConfiguration(configuration, dm)
        }*/

        tooltipService.initialize(applicationContext, findViewById(R.id.rootView))
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            tooltipService.hideTooltip()
        }

        return super.dispatchTouchEvent(ev)
    }
}