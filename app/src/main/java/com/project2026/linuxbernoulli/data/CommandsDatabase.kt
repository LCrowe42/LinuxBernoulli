package com.project2026.linuxbernoulli.data

import androidx.room3.*
import com.project2026.linuxbernoulli.data.model.Command

@Dao
interface CommandsDao {
    @Query("select id, name, description, type, favorite from commands")
    suspend fun getCommands(): List<Command>

    @Insert
    suspend fun addCommand(song: Command)

    @Delete
    suspend fun deleteCommand(song: Command)

    @Update
    suspend fun updateCommand(song: Command)
}

@Database(entities = [Command::class], version = 2, exportSchema = false)
abstract class CommandsDatabase : RoomDatabase() {
    abstract fun CommandsDao(): CommandsDao
}
