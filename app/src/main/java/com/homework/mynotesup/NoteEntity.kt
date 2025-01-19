package com.homework.mynotesup

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: Long,
    val text: String,
    val isCompleted: Boolean,
    val createdAt: Long
)