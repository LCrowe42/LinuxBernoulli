package com.project2026.linuxbernoulli.data.impl

import com.project2026.linuxbernoulli.data.ICommandsRepository
import com.project2026.linuxbernoulli.data.model.Command
import kotlinx.coroutines.delay

class CommandsRepository : ICommandsRepository {

    private var _commands = listOf<Command>()

    init {
        _commands = listOf(
            Command(
                id = "1",
                name = "ls",
                description = "Lists files and directories in the current directory.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "2",
                name = "cd",
                description = "Changes the current working directory.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "3",
                name = "pwd",
                description = "Prints the full path of the current working directory.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "4",
                name = "mkdir",
                description = "Creates a new directory.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "5",
                name = "echo",
                description = "Displays text or variable values in the terminal.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "6",
                name = "touch",
                description = "Creates a new empty file or updates a file timestamp.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "7",
                name = "cp",
                description = "Copies files or directories from one location to another.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "8",
                name = "mv",
                description = "Moves or renames files and directories.",
                type = 1,
                favorite = false
            ),
            Command(
                id = "9",
                name = "rm",
                description = "Removes files or directories.",
                type = 1,
                favorite = false
            )
        )
    }

    override suspend fun getCommands(): List<Command> {
        return _commands
    }

    override suspend fun deleteCommand(command: Command) {
        delay(2000)
        _commands = _commands.filter { c -> c.id != command.id}
    }

    override suspend fun addCommand(command: Command) {
        delay(2000)
        _commands = listOf(command) + _commands
    }

    override suspend fun toggleFavorite(command: Command) {
        delay(2000)
        _commands = _commands.map { c ->
            if (c.id == command.id) {
                c.copy(favorite = !c.favorite)
            } else {
                c
            }
        }
    }
}