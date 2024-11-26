import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import entity.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import service.TodoService
import java.util.*

class MainViewModel(
    private val todoService: TodoService
) : ViewModel() {
    val todos = MutableStateFlow<MutableList<Todo>>(mutableListOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            todoService.getAll().collect {
                todos.value = it.toMutableList()
            }
        }
    }

    fun add(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoService.add(title)
        }
    }

    fun getById(id: UUID) {
        println(todoService.getById(id))
    }

    suspend fun deleteById(id: UUID) {
        return todoService.deleteById(id)
    }

}