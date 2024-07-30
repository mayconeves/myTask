package com.mytasks.adapter

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.mytasks.MainActivity
import com.mytasks.R
import com.mytasks.model.Task
import com.mytasks.repository.TaskRepository
import com.mytasks.ui.theme.RadioButtonHighSelected
import com.mytasks.ui.theme.RadioButtonLowSelected
import com.mytasks.ui.theme.RadioButtonMediumSelected
import com.mytasks.ui.theme.ShapeCardPriority
import com.mytasks.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskItem(
    position: Int,
    taskList: MutableList<Task>,
    context: Context,
    navController: NavController
) {
    val title = taskList[position].task
    val description = taskList[position].description
    val priority = taskList[position].priority

    val scope = rememberCoroutineScope()
    val taskRepository = TaskRepository()

    fun dialogDelete() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Deletar tarefa")
            .setMessage("Deseja excluir a tarefa?")
            .setPositiveButton("Sim") { _, _ ->
                taskRepository.deleteTask(title.toString())

                scope.launch(Dispatchers.Main) {
                    taskList.removeAt(position)
                    navController.navigate(MainActivity.ROUTES_TASK_LIST)
                    Toast.makeText(context, "Sucesso ao deletar tarefa", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Não"){ _, _ ->

            }
            .show()
    }

    val priorityLevel: String = when (priority) {
        1 -> {
            "Média prioridade"
        }

        2 -> {
            "Alta prioridade"
        }

        else -> {
            "Baixa prioridade"
        }
    }

    var priorityColor = when (priority) {
        1 -> {
            RadioButtonMediumSelected
        }

        2 -> {
            RadioButtonHighSelected
        }

        else -> {
            RadioButtonLowSelected
        }
    }

    // CardView
    Card(
        // Background
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {

            val txtTitle = createRef()
            val txtDescription = createRef()
            val cardPriority = createRef()
            val txtPriority = createRef()
            val btnDelete = createRef()

            Text(
                text = title.toString(),
                modifier = Modifier.constrainAs(txtTitle) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = description.toString(),
                modifier = Modifier.constrainAs(txtDescription) {
                    top.linkTo(txtTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = priorityLevel,
                modifier = Modifier.constrainAs(txtPriority) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = priorityColor
                ),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPriority) {
                        top.linkTo(txtDescription.bottom, margin = 10.dp)
                        start.linkTo(txtPriority.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapeCardPriority.large

            ) {

            }

            IconButton(
                onClick = {
                    dialogDelete()
                },
                modifier = Modifier.constrainAs(btnDelete) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(cardPriority.end, margin = 50.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = null
                )
            }
        }
    }
}

//@Composable
//@Preview
//fun TaskItemPreview() {
//    TaksItem()
//}