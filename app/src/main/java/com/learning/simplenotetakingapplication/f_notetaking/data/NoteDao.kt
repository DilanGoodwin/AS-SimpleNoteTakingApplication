package com.learning.simplenotetakingapplication.f_notetaking.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.learning.simplenotetakingapplication.f_notetaking.domain.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao{
    @Query("SELECT * FROM note")
    fun getNote():Flow<List<Note>>

    @Upsert
    suspend fun upsertNote(note: Note)
}