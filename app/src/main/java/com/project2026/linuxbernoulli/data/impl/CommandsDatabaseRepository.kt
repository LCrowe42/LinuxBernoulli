package com.project2026.linuxbernoulli.data.impl

import android.app.Application
import androidx.room3.Room
import com.project2026.linuxbernoulli.data.ICommandsRepository
import com.project2026.linuxbernoulli.data.model.Command
import com.project2026.linuxbernoulli.data.CommandsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import kotlin.random.Random

class CommandsDatabaseRepository(app: Application) : ICommandsRepository {

    private val commandsDatabase: CommandsDatabase = getInstance(app)

    companion object {
        @Volatile
        private var INSTANCE: CommandsDatabase? = null

        fun getInstance(app: Application): CommandsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context = app,
                    klass = CommandsDatabase::class.java,
                    name = "commands.db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                    .also {
                        INSTANCE = it
                        // Seed on first run using a coroutine
                        runBlocking {
                            if (INSTANCE?.CommandsDao()?.getCommands()?.first()?.isEmpty() == true) {
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "1", name = "ls", description = "Lists files and directories.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "2", name = "cd", description = "Changes the current working directory.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "3", name = "pwd", description = "Prints the full path of the current working directory.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "4", name = "mkdir", description = "Creates a new directory.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "5", name = "rm", description = "Removes files or directories.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "6", name = "cp", description = "Copies files or directories from one location to another.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "7", name = "mv", description = "Moves or renames files and directories.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "8", name = "touch", description = "Creates a new empty file or updates a file's timestamp.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "9", name = "cat", description = "Displays the contents of a file in the terminal.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "10", name = "grep", description = "Searches for a pattern within files and prints matching lines.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "11", name = "find", description = "Searches for files and directories matching given criteria.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "12", name = "chmod", description = "Changes the permissions of a file or directory.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "13", name = "chown", description = "Changes the owner and group of a file or directory.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "14", name = "sudo", description = "Executes a command with superuser or administrator privileges.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "15", name = "apt", description = "Manages packages on Debian-based systems, including install and remove.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "16", name = "ps", description = "Displays a snapshot of currently running processes.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "17", name = "kill", description = "Sends a signal to a process, typically used to terminate it.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "18", name = "top", description = "Displays real-time system resource usage and running processes.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "19", name = "df", description = "Reports the amount of disk space used and available on filesystems.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "20", name = "free", description = "Displays the amount of free and used memory in the system.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "21", name = "echo", description = "Displays a line of text or a variable value in the terminal.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "22", name = "ping", description = "Tests network connectivity by sending packets to a host.", type = 1, favorite = false))
                                INSTANCE?.CommandsDao()?.addCommand(Command(id = "23", name = "ssh", description = "Opens a secure remote shell connection to another machine.", type = 1, favorite = false))
                            }
                        }
                    }
            }
        }
    }

    override fun getCommands(): Flow<List<Command>> {
        return commandsDatabase.CommandsDao().getCommands()
    }

    override suspend fun deleteCommand(command: Command) {
        //delay(2000)
        commandsDatabase.CommandsDao().deleteCommand(command)
    }

    override suspend fun addCommand(command: Command) {
        commandsDatabase.CommandsDao().addCommand(command)
    }

    override suspend fun toggleFavorite(command: Command) {
        commandsDatabase.CommandsDao().updateCommand(command)
    }

    override suspend fun cotdSelect(): String {
        val commands = commandsDatabase.CommandsDao().getCommands().first()

        if (commands.isEmpty()) {
            return "No commands found in database."
        }

        val today = LocalDate.now()
        val seed = today.year * 10000 + today.monthValue * 100 + today.dayOfMonth

        val selectedCommand =
            commands[Random(seed).nextInt(commands.size)]

        return "Command: %1\$s\nWhat it Does: %2\$s".format(
            selectedCommand.name,
            selectedCommand.description
        )
    }
}