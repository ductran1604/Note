package com.example.notedagger.adapter

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notedagger.model.Note
import com.example.notedagger.viewmodel.NoteViewModel

class SwipeDeleteItem(
    private val noteAdapter: NoteAdapter
) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        if (direction == ItemTouchHelper.LEFT ) {
            noteAdapter.deleteNote(position)
            noteAdapter.notifyDataSetChanged()
            Toast.makeText(viewHolder.itemView.context, "Deleted", Toast.LENGTH_SHORT).show()
        }
    }

}