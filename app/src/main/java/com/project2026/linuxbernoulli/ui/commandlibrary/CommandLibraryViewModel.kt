package com.project2026.linuxbernoulli.ui.commandlibrary

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.project2026.linuxbernoulli.data.ICommandsRepository
import com.project2026.linuxbernoulli.data.impl.CommandsRepository
import com.project2026.linuxbernoulli.data.model.Command

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
        CommandsRepository()

    init {
        viewModelScope.launch {
            _waiting.value = true

            _commands.value = repository.getCommands()

            _waiting.value = false
        }
    }

    fun addCommand(command: Command) {
        viewModelScope.launch {
            _waiting.value = true
            repository.addCommand(command)
            _commands.value = repository.getCommands()
            _waiting.value = false
        }
    }

    fun deleteCommand() {
        if (dialog.commandToDelete == null) return

        viewModelScope.launch {
            _waiting.value = true
            repository.deleteCommand(dialog.commandToDelete!!)
            _commands.value = repository.getCommands()
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
            _commands.value = repository.getCommands()

            _waiting.value = false
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