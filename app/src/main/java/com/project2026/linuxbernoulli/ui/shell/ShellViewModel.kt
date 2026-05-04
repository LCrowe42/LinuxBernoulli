package com.project2026.linuxbernoulli.ui.shell

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.project2026.linuxbernoulli.data.ICommandsRepository
import com.project2026.linuxbernoulli.data.impl.CommandsDatabaseRepository
import com.project2026.linuxbernoulli.ui.shell.ShellInterpreter

data class ShellLine(val text: String, val isInput: Boolean)

class ShellViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _lines: MutableState<List<ShellLine>> = mutableStateOf(listOf(
        ShellLine("Linux Bernoulli Shell Simulator", isInput = false),
        ShellLine("Type a command and press Enter.", isInput = false),
        ShellLine("Type 'help' for a list of commands.", isInput = false)
    ))
    val lines: State<List<ShellLine>> = _lines

    private val _input: MutableState<String> = mutableStateOf("")
    val input: State<String> = _input

    private val repository: ICommandsRepository =
        CommandsDatabaseRepository(application)

    fun onInputChanged(value: String) {
        _input.value = value
    }

    fun onSubmit() {
        val raw = _input.value.trim()
        if (raw.isEmpty()) return

        // copy input to line
        _lines.value += ShellLine("$ $raw", isInput = true)

        // Clear input
        _input.value = ""

        val interpreter = ShellInterpreter()
        val lineText = interpreter.parseCommand(raw)
        _lines.value += ShellLine(lineText, isInput = false)
    }

    fun onClear() {
        _lines.value = listOf()
    }
}