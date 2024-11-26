package di

import MainViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import repository.TodoRepository
import repository.impl.TodoRepositoryImpl
import service.TodoService
import service.impl.TodoServiceImpl

val appModule = module {
    single<TodoRepository> {
        TodoRepositoryImpl()
    }
    single<TodoService> {
        TodoServiceImpl(get())
    }
    viewModel { MainViewModel(get()) }
}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}