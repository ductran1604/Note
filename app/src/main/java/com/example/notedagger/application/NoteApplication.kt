package com.example.notedagger.application

import android.app.Application
import com.example.notedagger.component.DaggerNoteComponent


class NoteApplication: Application() {
    val noteComponent = DaggerNoteComponent.builder().application(this).build()
}