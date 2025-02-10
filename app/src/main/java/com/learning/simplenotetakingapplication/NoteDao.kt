package com.learning.simplenotetakingapplication

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao{
    @Query("SELECT * FROM note WHERE uid LIKE :noteId")
    fun getNote(noteId:Int):Note

    @Upsert
    suspend fun upsertNote(note:Note)
}