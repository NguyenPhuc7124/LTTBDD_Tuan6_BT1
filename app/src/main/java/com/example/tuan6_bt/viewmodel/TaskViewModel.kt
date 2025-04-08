package com.example.tuan6_bt.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tuan6_bt.model.Task

class TaskViewModel : ViewModel() {
    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val tasks: LiveData<MutableList<Task>> get() = _tasks

    fun addTask(title: String, description: String) {
        val newTask = Task(_tasks.value?.size ?: 0, title, description)
        _tasks.value?.add(newTask)
        _tasks.value = _tasks.value // Trigger update
    }

    fun toggleTaskCompletion(position: Int) {
        _tasks.value?.get(position)?.isCompleted = !_tasks.value!![position].isCompleted
        _tasks.value = _tasks.value // Trigger update
    }

    fun deleteTask(position: Int) {
        _tasks.value?.removeAt(position)
        _tasks.value = _tasks.value // Trigger update
    }
}