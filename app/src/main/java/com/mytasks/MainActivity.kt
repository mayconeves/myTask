package com.mytasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mytasks.ui.theme.MyTasksTheme
import com.mytasks.view.TaskList
import com.mytasks.view.TaskSave

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTasksTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ROUTES_TASK_LIST) {
                    composable(
                        route = ROUTES_TASK_LIST
                    ) {
                        TaskList(navController)
                    }

                    composable(
                        route = ROUTES_TASK_SAVE
                    ) {
                        TaskSave(navController)
                    }
                }
            }
        }
    }

    companion object ConstRoutes {
        const val ROUTES_TASK_LIST = "taskList"
        const val ROUTES_TASK_SAVE = "taskSave"
    }
}


