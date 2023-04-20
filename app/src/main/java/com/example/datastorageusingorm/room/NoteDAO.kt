package com.example.datastorageusingorm.room

import androidx.room.*


@Dao
interface NoteDAO {

    @Insert
    fun insertNote(note: NoteData)

    @Query("SELECT * FROM NoteData ORDER BY id DESC ")
    fun getDataNote() : List<NoteData>

    @Delete
    fun deleteNote(note: NoteData)

    @Update
    fun updateNote(note: NoteData)
}