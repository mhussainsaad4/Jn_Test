package com.example.jntest.application

import android.app.Application
import android.content.Context

class JnApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
    }
}