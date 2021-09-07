package com.example.motilaltask.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.motilaltask.database.entity.Repository

@Dao
interface RepositoryDao {

    @Query("SELECT * FROM Repository ORDER BY srno DESC")
    fun getAllRepositories(): LiveData<List<Repository>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepository(repository: Repository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertRepositories(repositories: List<Repository>)

    @Update
     fun updateRepository(repository: Repository)

    @Delete
     fun deleteRepository(repository: Repository)

    @Query("DELETE FROM Repository")
    fun deleteAll()
}