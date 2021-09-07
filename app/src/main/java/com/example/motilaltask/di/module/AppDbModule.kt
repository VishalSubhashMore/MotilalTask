package com.example.motilaltask.di.module

import android.content.Context
import com.example.motilaltask.application.MyApplication
import com.example.motilaltask.database.AppDatabase
import com.example.motilaltask.database.dao.RepositoryDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDbModule(val application: MyApplication) {

    @Singleton
    @Provides
    fun getUserDao(appDatabase: AppDatabase): RepositoryDao {
        return appDatabase.getRepositoryDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(): AppDatabase {
        return AppDatabase.getAppDatabaseInstance(getApplicationContext())
    }

    @Singleton
    @Provides
    fun getApplicationContext(): Context {
        return application.applicationContext
    }

}