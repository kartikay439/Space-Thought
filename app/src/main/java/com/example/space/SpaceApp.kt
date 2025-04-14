package com.example.space

import android.app.Application
import com.example.data.di.dataModule
import com.example.space.di.appModule
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