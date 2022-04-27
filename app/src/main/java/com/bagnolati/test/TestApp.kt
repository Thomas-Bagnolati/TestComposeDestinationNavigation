package com.bagnolati.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TestApp : Application() {
    init {
        Timber.plant(Timber.DebugTree())
    }
}