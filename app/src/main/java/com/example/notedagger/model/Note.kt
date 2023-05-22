package com.example.notedagger.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note_db")
data class Note(
    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_date")
    val timeDate: String,

    @ColumnInfo(name = "note_content")
    val content: String,

    @ColumnInfo(name = "note_time_edited")
    val timeEdited: String
): Serializable{
    @ColumnInfo(name = "note_id")
    @PrimaryKey(true)
    var id :Int = 0


}
