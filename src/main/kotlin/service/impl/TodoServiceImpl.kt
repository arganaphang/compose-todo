package service.impl

import entity.Todo
import kotlinx.coroutines.flow.Flow
import repository.TodoRepository
import service.TodoService
import java.util.*

class TodoServiceImpl(
    private val todoRepository: TodoRepository
) : TodoService {

    override suspend fun add(title: String) = todoRepository.add(title)

    override fun getAll(): Flow<List<Todo>> = todoRepository.getAll()

    override fun getById(id: UUID): Todo = todoRepository.getById(id)

    override suspend fun deleteById(id: UUID) = todoRepository.deleteById(id)

}