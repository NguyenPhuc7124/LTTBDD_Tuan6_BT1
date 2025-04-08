package com.example.tuan6_bt.UI_Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tuan6_bt.viewmodel.TaskViewModel

@Composable
fun ListScreen(navController: NavController, viewModel: TaskViewModel) {
    val tasks = viewModel.tasks.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "List", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addTask") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(tasks.value) { task ->
                TaskItem(task = task, viewModel = viewModel, index = tasks.value.indexOf(task), navController)
            }
        }
    }
}

@Composable
fun TaskItem(task: com.example.tuan6_bt.model.Task, viewModel: TaskViewModel, index: Int, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = if (task.isCompleted) Color(0xFF90EE90) else Color(0xFFFFB6C1)
            ),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { viewModel.toggleTaskCompletion(index) },
                modifier = Modifier.padding(start = 8.dp)
            )
            IconButton(onClick = { viewModel.deleteTask(index) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}