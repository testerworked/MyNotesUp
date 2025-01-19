package com.homework.mynotesup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotesAdapter(
    private val notes: MutableList<Note>,
    private val onNoteCheckedChange: (Note, Boolean) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = notes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNoteNumber: TextView = itemView.findViewById(R.id.tvNoteNumber)
        private val tvNoteText: TextView = itemView.findViewById(R.id.tvNoteText)
        private val cbNoteCompleted: CheckBox = itemView.findViewById(R.id.cbNoteCompleted)
        private val tvNoteDate: TextView = itemView.findViewById(R.id.tvNoteDate)

        fun bind(note: Note) {
            tvNoteNumber.text = (adapterPosition + 1).toString()
            tvNoteText.text = note.text
            cbNoteCompleted.isChecked = note.isCompleted
            tvNoteDate.text = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date(note.createdAt))

            cbNoteCompleted.setOnCheckedChangeListener { _, isChecked ->
                onNoteCheckedChange(note, isChecked)
            }
        }
    }
}