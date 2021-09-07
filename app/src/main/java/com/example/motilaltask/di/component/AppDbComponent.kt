package com.example.motilaltask.di.component

import com.example.motilaltask.di.module.AppDbModule
import com.example.motilaltask.viewmodel.MainViewModel
import com.example.motilaltask.work.DownloadWork
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppDbModule::class])
interface AppDbComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject(downloadWork: DownloadWork)
}