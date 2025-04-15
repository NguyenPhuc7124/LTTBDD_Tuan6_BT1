package com.example.tuan6_bt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tuan6_bt.dao.TaskDao
import com.example.tuan6_bt.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {
    val tasks: LiveData<List<Task>> = taskDao.getAllTasks().asLiveData()

    fun addTask(title: String, description: String) {
        val newTask = Task(title = title, description = description)
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insertTask(newTask)
        }
    }

    fun toggleTaskCompletion(task: Task) {
        val updatedTask = task.copy(isCompleted = !task.isCompleted)
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.updateTask(updatedTask)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }
}