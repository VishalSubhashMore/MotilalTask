package com.example.motilaltask.application

import android.app.Application
import com.example.motilaltask.di.component.AppDbComponent
import com.example.motilaltask.di.component.DaggerAppDbComponent
import com.example.motilaltask.di.component.DaggerRetroComponent
import com.example.motilaltask.di.component.RetroComponent
import com.example.motilaltask.di.module.AppDbModule
import com.example.motilaltask.di.module.RetroModule
import com.example.motilaltask.utils.Constants

class MyApplication : Application() {

    private lateinit var retroComponent: RetroComponent
    private lateinit var appDbComponent: AppDbComponent

    override fun onCreate() {
        super.onCreate()
        retroComponent =
            DaggerRetroComponent.builder().retroModule(RetroModule(Constants.baseUrl)).build()
        appDbComponent = DaggerAppDbComponent.builder().appDbModule(AppDbModule(this)).build()
    }

    fun getRetroComponent(): RetroComponent {
        return retroComponent
    }

    fun getAppDbComponent(): AppDbComponent {
        return appDbComponent
    }

}