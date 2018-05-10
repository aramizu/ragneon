package br.com.ragneon.config

import android.app.Application
import android.content.Context
import io.realm.Realm

class AndroidApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: AndroidApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        this.initComponents()
    }

    private fun initComponents() {
        Realm.init(this)
    }
}
