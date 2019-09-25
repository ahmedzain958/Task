package com.zainco.task

import android.app.Application

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this)
    }
}