package com.example.notedagger.component

import android.app.Application
import com.example.notedagger.activity.MainActivity
import com.example.notedagger.activity.UpdateNoteActivity
import com.example.notedagger.database.repository.NoteRepository
import com.example.notedagger.module.NoteModule
import com.example.notedagger.viewmodel.NoteViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NoteModule::class])
interface NoteComponent {

    fun getNoteViewModel(): NoteViewModel

    fun inject(mainActivity: MainActivity)

    fun inject(updateNoteActivity: UpdateNoteActivity)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): NoteComponent
    }
}