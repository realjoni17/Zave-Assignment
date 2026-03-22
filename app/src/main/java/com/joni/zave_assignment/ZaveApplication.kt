package com.joni.zave_assignment

import android.app.Application
import com.joni.zave_assignment.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZaveApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(modules = module)
        }
    }
}