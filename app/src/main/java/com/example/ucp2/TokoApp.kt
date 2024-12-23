package com.example.ucp2

import android.app.Application
import com.example.ucp2.dependenciesinjection.ContainerApp

class TokoApp : Application() {
    lateinit var ContainerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        ContainerApp = ContainerApp(this)
    }

}