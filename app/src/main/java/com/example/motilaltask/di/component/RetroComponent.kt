package com.example.motilaltask.di.component

import com.example.motilaltask.api.RetroServiceInterface
import com.example.motilaltask.di.module.RetroModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetroModule::class])
interface RetroComponent {
    fun getRetroServiceInterface(): RetroServiceInterface
}