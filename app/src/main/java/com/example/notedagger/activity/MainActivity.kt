package com.example.notedagger.activity

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notedagger.R
import com.example.notedagger.adapter.NoteItemDecoration
import com.example.notedagger.adapter.NoteAdapter
import com.example.notedagger.adapter.SwipeDeleteItem
import com.example.notedagger.application.NoteApplication
import com.example.notedagger.constant.NoteAction
import com.example.notedagger.databinding.ActivityMainBinding
import com.example.notedagger.model.Note
import com.example.notedagger.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.Exception
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var noteRV: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding

//    private val noteViewModel by lazy {
//        ViewModelProvider(
//            this,
//            NoteViewModel.NoteViewModelFactory(application)
//        )[NoteViewModel::class.java]
//    }

    @Inject
    lateinit var noteViewModel: NoteViewModel

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode) {
                RESULT_OK -> {
                    val intent = it.data
                    val note = intent?.getSerializableExtra(NoteAction.ADDING_NOTE) as Note
                    if (note.title.isEmpty() || note.content.isEmpty()) {
                        Toast.makeText(
                            this,
                            "You must type your title and content",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        noteViewModel.insertNote(note)
                    }

                }
                //else -> throw Exception("Cannot get result ")
            }
        }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        //Inject mainActivity to define noteViewModel
        (application as NoteApplication).noteComponent.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddingNoteActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        initNoteList()


    }


    @SuppressLint("NotifyDataSetChanged")
    private fun initNoteList() {


        noteRV = binding.noteRV
        noteRV.layoutManager = LinearLayoutManager(this)
        noteRV.addItemDecoration(NoteItemDecoration(20))
        noteAdapter = NoteAdapter(noteViewModel) { note, view ->
            updateNote(note, view)
        }
        noteRV.adapter = noteAdapter

        //Enable feature swipe to delete
        val swipeDeleteItem = SwipeDeleteItem(noteAdapter)
        val itemTouchHelper = ItemTouchHelper(swipeDeleteItem)
        itemTouchHelper.attachToRecyclerView(noteRV)



        noteViewModel.getAllNotes().observe(this) {
            noteAdapter.setNoteList(it)
            noteAdapter.notifyDataSetChanged()
        }
    }

    private fun updateNote(note: Note, view: View) {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra(NoteAction.UPDATE_NOTE, note)
        activityResultLauncher.launch(
            intent,
            ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                view, "Transition"
            )
        )
    }
}