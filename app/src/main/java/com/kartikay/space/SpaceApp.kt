package com.kartikay.space

import android.app.Application
import com.kartikay.data.di.dataModule
import com.kartikay.space.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class SpaceApp: Application() {
    override fun onCreate() {
        super.onCreate()
      startKoin{
    androidContext(this@SpaceApp)
          modules(
              appModule,
              dataModule
          )
      }
    }
}