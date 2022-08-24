package com.sixtassignment

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.sixtassignment.di.appcomponents.appComponent
import org.koin.core.context.GlobalContext.startKoin


class AssignemntApplication : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: AssignemntApplication

        private var isAppVisible: Boolean = false
        fun applicationContext(): Context {
            return instance.applicationContext
        }



        fun getInstance(): AssignemntApplication {
            return instance
        }

        fun setInstance(application: AssignemntApplication) {
            instance = application
        }

        fun isApplicationVisible(): Boolean {
            return isAppVisible
        }

        fun setAppVisible(isVisible: Boolean) {
            isAppVisible = isVisible
        }


    }
    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(appComponent)

        }
    }
}