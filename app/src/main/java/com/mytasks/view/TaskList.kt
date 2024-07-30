package com.mytasks.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mytasks.MainActivity
import com.mytasks.R
import com.mytasks.adapter.TaskItem
import com.mytasks.repository.TaskRepository
import com.mytasks.ui.theme.Black
import com.mytasks.ui.theme.Purple700
import com.mytasks.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskList(
    navController: NavController
) {

    val taskRepository = TaskRepository()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        // TopBar
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de Tarefas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple700
                )

            )
        },
        // Background
        containerColor = Black,
        // FloatingButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(MainActivity.ROUTES_TASK_SAVE)
                },
                containerColor = Purple700
            ) {
                Image(
                    imageVector =
                    ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Icone para salvar"
                )
            }
        },

    ) { innerPadding ->

        val listTask = taskRepository.getTasks().collectAsState(mutableListOf()).value

        // Items em coluna vertical
        LazyColumn(
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ) {

            itemsIndexed(listTask) { position, _ ->
                TaskItem(
                    position = position,
                    taskList = listTask,
                    context = context,
                    navController = navController)
            }

        }
    }
}