package com.example.notedagger.module

import android.app.Application
import com.example.notedagger.database.NoteDatabase
import com.example.notedagger.database.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NoteModule {

    @Provides
    fun getNoteDatabase(application: Application) = NoteDatabase.getInstance(application)

    @Provides
    fun getNoteDao(noteDatabase: NoteDatabase) = noteDatabase.getNoteDao()
}