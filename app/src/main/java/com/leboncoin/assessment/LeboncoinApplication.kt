package com.leboncoin.assessment

import android.app.Application
import com.leboncoin.assessment.di.applicationInjectionsModules
import org.koin.android.ext.android.startKoin

class LeboncoinApplication : Application() {

    companion object {
        private val LOG_TAG = LeboncoinApplication::class.java.simpleName
        lateinit var instance: LeboncoinApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, applicationInjectionsModules)
    }

}