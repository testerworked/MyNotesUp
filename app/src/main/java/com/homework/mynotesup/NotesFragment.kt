package com.homework.mynotesup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotesFragment : Fragment() {

    private lateinit var adapter: NotesAdapter
    private val notes = mutableListOf<Note>()
    private lateinit var db: AppDatabase
    private lateinit var noteDao: NoteDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        // Инициализация базы данных
        db = AppDatabase.getDatabase(requireContext())
        noteDao = db.noteDao()

        // Загрузка заметок из базы данных
        loadNotes()

        // Инициализация RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvNotes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NotesAdapter(notes) { note, isChecked ->
            note.isCompleted = isChecked
            updateNoteInDb(note)
        }
        recyclerView.adapter = adapter

        // Инициализация поля ввода и кнопки добавления
        val etNote = view.findViewById<EditText>(R.id.etNote)
        val btnAddNote = view.findViewById<Button>(R.id.btnAddNote)

        btnAddNote.setOnClickListener {
            val noteText = etNote.text.toString()
            if (noteText.isNotEmpty()) {
                val newNote = Note(
                    id = System.currentTimeMillis(),
                    text = noteText,
                    isCompleted = false,
                    createdAt = System.currentTimeMillis()
                )
                notes.add(newNote)
                adapter.notifyItemInserted(notes.size - 1)
                etNote.text.clear()
                saveNoteToDb(newNote)
            }
        }

        return view
    }

    private fun loadNotes() {
        val noteEntities = noteDao.getAll()
        notes.clear()
        notes.addAll(noteEntities.map { Note(it.id, it.text, it.isCompleted, it.createdAt) })
    }

    private fun saveNoteToDb(note: Note) {
        val noteEntity = NoteEntity(note.id, note.text, note.isCompleted, note.createdAt)
        noteDao.insert(noteEntity)
    }

    private fun updateNoteInDb(note: Note) {
        val noteEntity = NoteEntity(note.id, note.text, note.isCompleted, note.createdAt)
        noteDao.update(noteEntity)
    }
}