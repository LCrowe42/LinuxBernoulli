package com.project2026.linuxbernoulli.data.impl

import android.app.Application
import androidx.room3.Room
import com.project2026.linuxbernoulli.data.ICommandsRepository
import com.project2026.linuxbernoulli.data.model.Command
import com.project2026.linuxbernoulli.data.CommandsDatabase
import kotlinx.coroutines.delay
import java.time.LocalDate
import kotlin.random.Random

class CommandsDatabaseRepository(app: Application) : ICommandsRepository {

    private val commandsDatabase: CommandsDatabase

    init {
        commandsDatabase = Room.databaseBuilder(
            context = app,
            klass = CommandsDatabase::class.java,
            name = "commands.db"
        ).fallbackToDestructiveMigration(true) // deletes the db on version change
            .build()
    }

    override suspend fun getCommands(): List<Command> {
        return commandsDatabase.CommandsDao().getCommands()
    }

    override suspend fun deleteCommand(command: Command) {
        delay(2000)
        commandsDatabase.CommandsDao().deleteCommand(command)
    }

    override suspend fun addCommand(command: Command) {
        commandsDatabase.CommandsDao().addCommand(command)
    }

    override suspend fun toggleFavorite(command: Command) {
        commandsDatabase.CommandsDao().updateCommand(command)
    }

    override suspend fun cotdSelect(): String {
        val commands = commandsDatabase.CommandsDao().getCommands()

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