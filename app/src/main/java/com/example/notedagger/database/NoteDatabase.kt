package com.example.notedagger.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notedagger.database.dao.NoteDao
import com.example.notedagger.model.Note

@Database(entities = [Note::class], version = 3)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private var instance: NoteDatabase? = null
        fun getInstance(application: Application): NoteDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(application.applicationContext,
                    NoteDatabase::class.java,
                    "Note Database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}