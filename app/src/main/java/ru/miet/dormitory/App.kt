package ru.miet.dormitory

import android.app.Application
import android.content.Context
import ru.miet.dormitory.ioc.ApplicationComponent

class App : Application() {
    val applicationComponent by lazy { ApplicationComponent() }

    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }
}
