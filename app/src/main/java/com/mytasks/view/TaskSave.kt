package com.mytasks.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mytasks.components.CustomButton
import com.mytasks.components.CustomEditText
import com.mytasks.repository.TaskRepository
import com.mytasks.ui.theme.Purple700
import com.mytasks.ui.theme.RadioButtonHighDisabled
import com.mytasks.ui.theme.RadioButtonHighSelected
import com.mytasks.ui.theme.RadioButtonLowDisabled
import com.mytasks.ui.theme.RadioButtonMediumDisabled
import com.mytasks.ui.theme.RadioButtonMediumSelected
import com.mytasks.ui.theme.White
import com.mytasks.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskSave(
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val taskRepository = TaskRepository()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Salvar Tarefa",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = White,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple700
                ),
            )
        },
    ) {
        // Status
        var titleTask by remember {
            mutableStateOf("")
        }

        var descriptionTask by remember {
            mutableStateOf("")
        }

        var lowPriority by remember {
            mutableStateOf(false)
        }

        var mediumPriority by remember {
            mutableStateOf(false)
        }

        var highPriority by remember {
            mutableStateOf(false)
        }


        // Definir layout da tela em uma unica coluna com scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            CustomEditText(
                value = titleTask,
                onValueChange = {
                    titleTask = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 120.dp, 20.dp, 0.dp),
                label = "Titulo",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )

            CustomEditText(
                value = descriptionTask,
                onValueChange = {
                    descriptionTask = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 20.dp, 20.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Nível de prioridade"
                )
                RadioButton(
                    selected = lowPriority,
                    onClick = {
                        lowPriority = !lowPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonLowDisabled,
                        selectedColor = RadioButtonLowDisabled
                    )
                )

                RadioButton(
                    selected = mediumPriority,
                    onClick = {
                        mediumPriority = !mediumPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonMediumDisabled,
                        selectedColor = RadioButtonMediumSelected
                    )
                )

                RadioButton(
                    selected = highPriority,
                    onClick = {
                        highPriority = !highPriority
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonHighDisabled,
                        selectedColor = RadioButtonHighSelected
                    )
                )
            }

            CustomButton(
                onClick = {
                    var msg: Boolean = true

                    // Abrir thread paralela
                    scope.launch(Dispatchers.IO) {
                        // validacoes
                        if (titleTask.isEmpty()) {
                            msg = false
                        } else if (
                            titleTask.isNotBlank() && descriptionTask.isNotEmpty() && lowPriority
                        ) {
                            taskRepository.saveTask(
                                titleTask,
                                descriptionTask,
                                Constants.LOW_PRIORITY
                            )
                            msg = true
                        } else if (
                            titleTask.isNotBlank() && descriptionTask.isNotEmpty() && mediumPriority
                        ) {
                            taskRepository.saveTask(
                                titleTask,
                                descriptionTask,
                                Constants.MEDIUM_PRIORITY
                            )
                            msg = true
                        } else if (
                            titleTask.isNotBlank() && descriptionTask.isNotEmpty() && highPriority
                        ) {
                            taskRepository.saveTask(
                                titleTask,
                                descriptionTask,
                                Constants.HIGH_PRIORITY
                            )
                            msg = true
                        } else if (
                            titleTask.isNotBlank() && lowPriority
                        ) {
                            taskRepository.saveTask(
                                titleTask,
                                descriptionTask,
                                Constants.LOW_PRIORITY
                            )
                            msg = true
                        } else if (
                            titleTask.isNotBlank() && mediumPriority
                        ) {
                            taskRepository.saveTask(
                                titleTask,
                                descriptionTask,
                                Constants.MEDIUM_PRIORITY
                            )
                            msg = true
                        } else if (
                            titleTask.isNotBlank() && highPriority
                        ) {
                            taskRepository.saveTask(
                                titleTask,
                                descriptionTask,
                                Constants.HIGH_PRIORITY
                            )
                            msg = true
                        } else {
                            taskRepository.saveTask(
                                titleTask,
                                descriptionTask,
                                Constants.LOW_PRIORITY
                            )
                            msg = true
                        }

                    }

                    // Retorno na main principal
                    scope.launch(Dispatchers.Main) {
                        if (msg) {
                            Toast.makeText(context, "Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT)
                                .show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(
                                context,
                                "Título da tarefa é obrigatório",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(20.dp),
                value = "Salvar"
            )

        }
    }
}