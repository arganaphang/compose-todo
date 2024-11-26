package service

import entity.Todo
import kotlinx.coroutines.flow.Flow
import java.util.*


interface TodoService {
    suspend fun add(title: String)
    fun getAll(): Flow<List<Todo>>
    fun getById(id: UUID): Todo
    suspend fun deleteById(id: UUID)
}