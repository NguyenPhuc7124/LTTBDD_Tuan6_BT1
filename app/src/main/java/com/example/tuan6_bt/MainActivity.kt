package com.example.tuan6_bt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.tuan6_bt.database.AppDatabase
import com.example.tuan6_bt.navigation.SetupNavGraph
import com.example.tuan6_bt.ui.theme.TaskAppTheme
import com.example.tuan6_bt.viewmodel.TaskViewModel
import com.example.tuan6_bt.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Khởi tạo cơ sở dữ liệu và TaskDao
        val database = AppDatabase.getDatabase(this)
        val taskDao = database.taskDao()
        // Khởi tạo ViewModel với factory
        viewModel = ViewModelProvider(this, TaskViewModelFactory(taskDao)).get(TaskViewModel::class.java)

        setContent {
            TaskAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    // Truyền viewModel đã khởi tạo vào SetupNavGraph
                    SetupNavGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}