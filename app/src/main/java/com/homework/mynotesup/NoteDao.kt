package com.homework.mynotesup

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll(): List<NoteEntity>

    @Insert
    fun insert(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    fun deleteById(id: Long)


        @Update
        fun update(note: NoteEntity)

        @Delete
        fun delete(note: NoteEntity)

}