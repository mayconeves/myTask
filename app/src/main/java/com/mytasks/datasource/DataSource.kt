package com.mytasks.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.mytasks.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<Task>> = _allTasks

    fun saveTask(
        title: String,
        description: String,
        priority: Int
    ) {
        // Criar campos no bd
        val taskMap = hashMapOf(
            "task" to title,
            "description" to description,
            "priority" to priority
        )

        db.collection("tasks").document(title).set(taskMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun getTasks(): Flow<MutableList<Task>> {
        val listTasks: MutableList<Task> = mutableListOf()

        db.collection("tasks").get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful) {
                for (doc in querySnapshot.result) {
                    val taskItem = doc.toObject(Task::class.java)
                    listTasks.add(taskItem)
                    _allTasks.value = listTasks
                }
            }
        }
        return allTasks
    }

    fun deleteTask(task: String) {
        db.collection("tasks").document(task).delete().addOnCompleteListener {

        }.addOnFailureListener {

        }
    }
}