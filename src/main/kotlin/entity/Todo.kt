package entity

import java.util.UUID


data class Todo(
    val id: UUID,
    val title: String,
    val isDone: Boolean,
    val createdAt: Long
)
