package pe.edu.idat.apppatitasidatsjm.util

import android.app.Application
import android.content.Context

class MiApp: Application() {
    init {
        instance = this
    }
    companion object{
        lateinit var instance: MiApp
            private set
        val applicationContext:  Context
            get() = instance.applicationContext
    }
}