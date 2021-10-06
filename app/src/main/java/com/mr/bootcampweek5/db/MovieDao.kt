package com.mr.bootcampweek5.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mr.bootcampweek5.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: Result)

    @Query("SELECT * FROM result_table")
    fun getAllMovies(): LiveData<List<Result>>

    @Delete
    suspend fun delete(result: Result)
}