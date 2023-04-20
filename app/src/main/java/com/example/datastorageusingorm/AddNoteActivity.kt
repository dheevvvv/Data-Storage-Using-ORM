package com.example.datastorageusingorm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.datastorageusingorm.databinding.ActivityAddNoteBinding
import com.example.datastorageusingorm.room.NoteData
import com.example.datastorageusingorm.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private var dbNote:NoteDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initiate db note
        dbNote = NoteDatabase.getInstance(this)
        binding.btnSaveNote.setOnClickListener {
            addNote()
        }
    }

    fun addNote(){
        GlobalScope.async {
            val title = binding.noteTitle.text.toString()
            val content = binding.noteContent.text.toString()
            val date = binding.noteDate.text.toString()

            dbNote?.noteDao()?.insertNote(NoteData(0, title, content, date))
        }
        finish()
    }
}