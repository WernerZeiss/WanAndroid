package com.zcrain.wanandroid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author CWQ
 * @date 11/24/20
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}