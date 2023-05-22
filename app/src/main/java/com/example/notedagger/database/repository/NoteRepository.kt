package com.example.notedagger.database.repository

import android.app.Application
import com.example.notedagger.database.NoteDatabase
import com.example.notedagger.database.dao.NoteDao
import com.example.notedagger.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(val noteDao: NoteDao) {

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteAllNotes() = noteDao.deleteAllNotes()
    fun getAllNotes() = noteDao.getAllNotes()
}