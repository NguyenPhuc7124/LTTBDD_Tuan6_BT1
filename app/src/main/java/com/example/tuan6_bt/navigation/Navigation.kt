package com.example.tuan6_bt.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tuan6_bt.UI_Screens.AddTaskScreen
import com.example.tuan6_bt.UI_Screens.ListScreen
import com.example.tuan6_bt.viewmodel.TaskViewModel

@Composable
fun SetupNavGraph(navController: NavHostController, viewModel: TaskViewModel) {
    NavHost(navController, startDestination = "list") {
        composable("list") { ListScreen(navController, viewModel) }
        composable("addTask") { AddTaskScreen(navController, viewModel) }
    }
}