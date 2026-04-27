package com.project2026.linuxbernoulli.data

import com.project2026.linuxbernoulli.data.model.Command

interface ICommandsRepository {
    suspend fun getCommands(): List<Command>
    suspend fun deleteCommand(command: Command)
    suspend fun addCommand(command: Command)
    suspend fun toggleFavorite(command: Command)
}