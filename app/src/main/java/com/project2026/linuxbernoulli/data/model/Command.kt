package com.project2026.linuxbernoulli.data.model

import androidx.room3.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "commands") // this creates a table
data class Command(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val type: Int,
    @SerializedName("favorite")
    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
) {
}
