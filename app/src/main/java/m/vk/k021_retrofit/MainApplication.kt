package m.vk.k021_retrofit

import android.app.Application
import m.vk.k021_retrofit.manager.Contexttor

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Contexttor.getInstance().init(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        Contexttor.getInstance().clear()
    }
}