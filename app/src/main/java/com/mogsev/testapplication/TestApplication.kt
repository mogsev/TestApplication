package com.mogsev.testapplication.gui.activities

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.mogsev.testapplication.BuildConfig
import timber.log.Timber

/**
 * Created by Eugene Sikaylo
 * email: mogsev@gmail.com
 */
class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // initialize Timber
        initTimber()
        //we use vector drawables
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}