package com.example.tuan6_bt.model

data class Task(
    val id: Int = 0,
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)