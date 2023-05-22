package com.example.notedagger.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notedagger.R
import com.example.notedagger.databinding.NoteItemLayoutBinding
import com.example.notedagger.model.Note
import com.example.notedagger.viewmodel.NoteViewModel

class NoteAdapter(
    private val noteViewModel: NoteViewModel,
    private val updateNote: (Note, View) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteList: List<Note> = emptyList()

    //private val viewBinderHelper: ViewBinderHelper = ViewBinderHelper()

    class NoteViewHolder(private val _noteItemLayoutBinding: NoteItemLayoutBinding) :
        RecyclerView.ViewHolder(_noteItemLayoutBinding.root) {
        val noteItemLayoutBinding: NoteItemLayoutBinding
            get() = _noteItemLayoutBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.note_item_layout,
                parent,
                false
            )
        )
    }

    fun setNoteList(noteList: List<Note>) {
        this.noteList = noteList
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteItemLayoutBinding.note = noteList[position]
        holder.noteItemLayoutBinding.root.setOnClickListener {
            updateNote(noteList[position], holder.noteItemLayoutBinding.root)
        }
        holder.noteItemLayoutBinding.contentToDo.maxLines = 1


    }

    fun deleteNote(position: Int){
        noteViewModel.deleteNote(noteList[position])
    }
}