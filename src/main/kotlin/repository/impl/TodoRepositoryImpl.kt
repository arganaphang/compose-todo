package repository.impl

import entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repository.TodoRepository
import java.util.*

class TodoRepositoryImpl : TodoRepository {
    private val _todos = MutableStateFlow<List<Todo>>(mutableListOf())

    override suspend fun add(title: String) {
        coroutineScope {
            launch(Dispatchers.IO) {
                _todos.update {
                    (it + Todo(
                        id = UUID.randomUUID(),
                        title = title,
                        isDone = false,
                        createdAt = System.currentTimeMillis()
                    ))
                }
            }
        }
    }

    override fun getAll(): Flow<List<Todo>> {
        return _todos
    }

    override fun getById(id: UUID): Todo {
        return _todos.value.first {
            it.id == id
        }
    }

    override suspend fun deleteById(id: UUID) {
        coroutineScope {
            launch(Dispatchers.IO) {
                _todos.update {
                    it.filter { item -> item.id !== id }
                }
            }
        }
    }

}