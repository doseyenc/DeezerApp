package com.example.deezerapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeezerAppApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}