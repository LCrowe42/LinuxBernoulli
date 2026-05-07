package com.project2026.linuxbernoulli.ui.commandlibrary

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.project2026.linuxbernoulli.data.ICommandsRepository
import com.project2026.linuxbernoulli.data.impl.CommandsDatabaseRepository
import com.project2026.linuxbernoulli.data.model.Command
import kotlinx.coroutines.flow.first

class CommandLibraryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _commands: MutableState<List<Command>> = mutableStateOf(listOf())
    val commands: State<List<Command>> = _commands

    private val _selected: MutableState<Command?> = mutableStateOf(null)
    val selectedCommand: State<Command?> = _selected

    private val _waiting: MutableState<Boolean> = mutableStateOf(false)
    val waiting: State<Boolean> = _waiting

    val dialog: ShellDialog = ShellDialog()

    // Local database repository
    private val repository: ICommandsRepository =
        CommandsDatabaseRepository(application)

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private var _allCommands: List<Command> = listOf()

    init {
        viewModelScope.launch {
            _waiting.value = true

            repository.getCommands().collect { list ->
                _allCommands = list
                _commands.value = list
                _waiting.value = false
            }


        }
    }

    fun addCommand(command: Command) {
        viewModelScope.launch {
            _waiting.value = true
            repository.addCommand(command)
            _commands.value = repository.getCommands().first()
            _waiting.value = false
        }
    }

    fun deleteCommand() {
        if (dialog.commandToDelete == null) return

        viewModelScope.launch {
            _waiting.value = true
            repository.deleteCommand(dialog.commandToDelete!!)
            _commands.value = repository.getCommands().first()
            _waiting.value = false
        }
    }

    fun toggleFavorite(command: Command) {
        viewModelScope.launch {
            _waiting.value = true

            val updatedCommand = command.copy(
                favorite = !command.favorite
            )

            repository.toggleFavorite(updatedCommand)
            _commands.value = repository.getCommands().first()

            _waiting.value = false
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        _commands.value = filterCommands(_allCommands, query)
    }

    private fun filterCommands(commands: List<Command>, query: String): List<Command> {
        if (query.isBlank()) return commands
        return commands.filter { c ->
            c.name.contains(query, ignoreCase = true) ||
                    c.description.contains(query, ignoreCase = true)
        }
    }

    fun selectCommand(command: Command) {
        _selected.value = command
    }

    class ShellDialog {
        private val _showDialog: MutableState<Boolean> = mutableStateOf(false)
        val showDialog: State<Boolean> = _showDialog

        var commandToDelete: Command? = null
            private set

        fun hideDialog() {
            commandToDelete = null
            _showDialog.value = false
        }

        fun showDialog(command: Command) {
            commandToDelete = command
            _showDialog.value = true
        }
    }
}