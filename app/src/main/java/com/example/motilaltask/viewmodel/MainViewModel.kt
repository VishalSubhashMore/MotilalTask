package com.example.motilaltask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.motilaltask.application.MyApplication
import com.example.motilaltask.database.dao.RepositoryDao
import com.example.motilaltask.database.entity.Repository
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repositoryDao: RepositoryDao

    init {
        (application as MyApplication).getAppDbComponent().inject(this)
    }

     fun getAllRepositories(): LiveData<List<Repository>> {
         return repositoryDao.getAllRepositories()
    }
}