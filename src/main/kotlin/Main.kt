import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.initializeKoin
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun app() {
    val viewModel = koinViewModel<MainViewModel>()
    val lists = viewModel.todos.collectAsState(initial = emptyList())
    Column {
        Button(onClick = {
            viewModel.add("Hello")
        }) {
            Text("Add Todos")
        }
        LazyColumn {
            itemsIndexed(lists.value) { idx, row ->
                Text("$idx ${row.title} ${row.isDone}")
            }
        }
    }
}

fun main() = application {
    initializeKoin()
    Window(onCloseRequest = ::exitApplication, title = "Todo List") {
        MaterialTheme {
            KoinContext {
                app()
            }
        }
    }
}
