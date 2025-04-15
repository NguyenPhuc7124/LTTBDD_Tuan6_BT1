package com.example.tuan6_bt.dao

import androidx.room.*
import com.example.tuan6_bt.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Insert
    fun insertTask(task: Task): Long

    @Update
    fun updateTask(task: Task): Int

    @Delete
    fun deleteTask(task: Task): Int
}