package com.lakue.androidsampleproject

import android.app.Application
import android.os.Build
import android.webkit.WebView
import com.lakue.androidsampleproject.utils.BaseUtils.init
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {
    companion object{
        lateinit var BaseApplication: BaseApplication

        fun getInstance(): BaseApplication{
            return BaseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        BaseApplication = this
        init(this)
    }
}