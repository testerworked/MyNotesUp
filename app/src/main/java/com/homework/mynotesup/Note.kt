package com.homework.mynotesup

data class Note(
    val id: Long,
    val text: String,
    var isCompleted: Boolean,
    val createdAt: Long // Время создания заметки в миллисекундах
)