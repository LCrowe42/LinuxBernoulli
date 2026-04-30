package com.project2026.linuxbernoulli.data

import com.project2026.linuxbernoulli.data.model.Command
import kotlinx.coroutines.flow.Flow

interface ICommandsRepository {
    fun getCommands(): Flow<List<Command>>
    suspend fun deleteCommand(command: Command)
    suspend fun addCommand(command: Command)
    suspend fun toggleFavorite(command: Command)
    suspend fun cotdSelect(): String
}