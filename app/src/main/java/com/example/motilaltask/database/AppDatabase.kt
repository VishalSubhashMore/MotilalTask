package com.example.motilaltask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.motilaltask.database.dao.RepositoryDao
import com.example.motilaltask.database.entity.Repository

@Database(entities = [Repository::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepositoryDao(): RepositoryDao

    companion object {
        private var dbInstance: AppDatabase? = null
        fun getAppDatabaseInstance(context: Context): AppDatabase {
            if (dbInstance == null) {
                dbInstance =
                    Room.databaseBuilder<AppDatabase>(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "Database"
                    )
                        .allowMainThreadQueries()
                        .build()
            }
            return dbInstance!!
        }
    }
}