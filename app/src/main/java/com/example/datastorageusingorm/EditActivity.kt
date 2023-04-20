package com.example.datastorageusingorm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.datastorageusingorm.databinding.ActivityEditNoteBinding
import com.example.datastorageusingorm.room.NoteData
import com.example.datastorageusingorm.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNoteBinding
    var dbNote:NoteDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbNote = NoteDatabase.getInstance(this)

        //        ambil data yg dikirim dari adapter
        var getDataNote = intent.getSerializableExtra("dataedit") as NoteData
//        set data yang dikirim ke dalam editText
        binding.editTitle.setText(getDataNote.title)
        binding.editNotee.setText(getDataNote.content)
        binding.idNote.setText(getDataNote.id.toString())
        binding.editDate.setText(getDataNote.date)

        //        klik edit, data akan diedit
        binding.btnEditNote.setOnClickListener {
            editNote()
        }
    }

    fun editNote() {
        var idNote = binding.idNote.text.toString().toInt()
        var title = binding.editTitle.text.toString()
        var note = binding.editNotee.text.toString()
        var date = binding.editDate.text.toString()


        GlobalScope.async {
            var edit = dbNote?.noteDao()?.updateNote(NoteData(idNote, title, note,date))
            runOnUiThread {
                Toast.makeText(this@EditActivity, "Dat berhasil di Edit", Toast.LENGTH_LONG)
                    .show()
            }
            finish()
        }
    }
}