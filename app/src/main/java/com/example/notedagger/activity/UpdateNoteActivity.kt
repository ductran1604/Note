package com.example.notedagger.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import com.example.notedagger.application.NoteApplication
import com.example.notedagger.constant.NoteAction
import com.example.notedagger.databinding.ActivityUpdateNoteBinding
import com.example.notedagger.model.Note
import com.example.notedagger.viewmodel.NoteViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNoteBinding
    @Inject
    lateinit var noteViewModel: NoteViewModel
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        //Inject updateNoteActivity to define noteViewModel
        (application as NoteApplication).noteComponent.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        note = intent.getSerializableExtra(NoteAction.UPDATE_NOTE) as Note
        binding.note = note


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun handleOnBackPressed() {
                val updateNote = bindUpdateNote()
                updateNote.id = note.id // let noteViewModel recognize the id of note that want to be updated

                if(updateNote.content != note.content|| updateNote.title != note.title ){
                    noteViewModel.updateNote(updateNote)
                }

                finish()
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindUpdateNote(): Note {
        val updateTitle = binding.edtNoteUpdateTitle.text.toString()
        val updateContent = binding.edtNoteUpdateContent.text.toString()

        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val localDate = LocalDate.now()
        val updateDate = localDate.format(formatter)

        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val localTime = LocalTime.now()
        val updateTimeEdited = localTime.format(timeFormatter)


        return Note(updateTitle, updateDate, updateContent, updateTimeEdited)
    }
}