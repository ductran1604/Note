package com.example.notedagger.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notedagger.database.repository.NoteRepository
import com.example.notedagger.model.Note
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {
   // private val noteRepository = NoteRepository(application)
    private val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.i("Error", throwable.toString())
        //Toast.makeText(, "Something error", Toast.LENGTH_SHORT).show()
    }

    fun insertNote(note: Note) = viewModelScope.launch(handler) {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note)= viewModelScope.launch(handler) {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(handler) {
        noteRepository.deleteNote(note)
    }

    fun deleteAllNotes()= viewModelScope.launch(handler) {
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes() = noteRepository.getAllNotes()

    class NoteViewModelFactory(private val noteRepository: NoteRepository): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NoteViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(noteRepository) as T
            }
            throw Exception("Cannot create view model")
        }
    }
}