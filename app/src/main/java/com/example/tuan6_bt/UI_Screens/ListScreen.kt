package com.example.tuan6_bt.UI_Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var showDialog by remember { mutableStateOf(false) }
    var selectedTask by remember { mutableStateOf<com.example.tuan6_bt.model.Task?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "List", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White) },
                backgroundColor = Color(0xFF2196F3),
                contentColor = Color.White
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addTask") },
                backgroundColor = Color(0xFF2196F3),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color(0xFF2196F3)
            ) {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { /* Handle navigation */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = "Calendar") },
                    label = { Text("Calendar") },
                    selected = false,
                    onClick = { /* Handle navigation */ }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Menu, contentDescription = "Menu") },
                    label = { Text("Menu") },
                    selected = false,
                    onClick = { /* Handle navigation */ }
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks.value) { task ->
                TaskItem(
                    task = task,
                    viewModel = viewModel,
                    index = tasks.value.indexOf(task),
                    navController = navController,
                    onItemClick = {
                        selectedTask = task
                        showDialog = true
                    }
                )
            }
        }

        // Dialog để hiển thị chi tiết
        if (showDialog && selectedTask != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(selectedTask!!.title, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                text = { Text(selectedTask!!.description ?: "No description available", fontSize = 16.sp) },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Close", color = Color(0xFF2196F3))
                    }
                }
            )
        }
    }
}

@Composable
fun TaskItem(
    task: com.example.tuan6_bt.model.Task,
    viewModel: TaskViewModel,
    index: Int,
    navController: NavController,
    onItemClick: () -> Unit
) {
    // Danh sách màu dựa trên hình, đảm bảo luân phiên
    val colors = listOf(
        Color(0xFFB3E5FC), // Xanh nhạt
        Color(0xFFFFB6C1), // Hồng
        Color(0xFF90EE90)  // Xanh lá
    )
    val colorIndex = index % colors.size // Đảm bảo màu thay đổi theo index

    Card(
        backgroundColor = colors[colorIndex],
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(color = colors[colorIndex], shape = RoundedCornerShape(8.dp))
            .clickable(onClick = onItemClick),
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
        ) {
            Text(
                text = task.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = task.description ?: "Finish the UI, integrate API, and write documentation",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}