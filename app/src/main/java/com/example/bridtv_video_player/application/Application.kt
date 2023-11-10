package com.example.bridtv_video_player.application

import android.app.Application
import com.example.bridtv_video_player.modules.core_module
import com.example.bridtv_video_player.modules.video_module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber


class Application : Application(){

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())

    }

    private fun initKoin() {
        val modules = listOf(
            core_module,
            video_module
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@Application)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules

            modules(modules)
        }
    }
}