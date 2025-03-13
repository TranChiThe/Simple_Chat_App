package com.example.chat_app

import android.app.Application
import com.example.chat_app.data.data_source.ObjectBoxStore
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

    }
}