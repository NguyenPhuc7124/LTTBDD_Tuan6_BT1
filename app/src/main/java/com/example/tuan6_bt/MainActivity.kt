package com.example.tuan6_bt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.tuan6_bt.navigation.SetupNavGraph
import com.example.tuan6_bt.ui.theme.TaskAppTheme
import com.example.tuan6_bt.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    val viewModel: TaskViewModel = viewModel()
                    SetupNavGraph(navController, viewModel)
                }
            }
        }
    }
}