package com.example.notedagger.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import com.example.notedagger.databinding.ActivityAddingNoteBinding
import com.example.notedagger.model.Note
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AddingNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddingNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            @RequiresApi(Build.VERSION_CODES.O)
            override fun handleOnBackPressed() {
                val noteTitle = binding.edtNoteTitle.text.toString()
                val noteContent = binding.edtNoteContent.text.toString()
                val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                val localDate = LocalDate.now()
                val noteDate = localDate.format(dateFormatter)

                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                val localTime = LocalTime.now()
                val noteTimeEdited = localTime.format(timeFormatter)

                val intent = Intent()
                val note = Note(noteTitle, noteDate, noteContent, noteTimeEdited)

                intent.putExtra(com.example.notedagger.constant.NoteAction.ADDING_NOTE, note)

                setResult(RESULT_OK, intent)
                finish()
            }
        } )
    }

}