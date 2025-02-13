package com.learning.simplenotetakingapplication.f_notetaking.data.data_source

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * NoteDao - DataAccessObject
 * Specifies the SQL queries for sending/receiving data to the database
 */
@Dao
interface NoteDao{
    @Query("SELECT * FROM note")
    fun getNotes():Flow<List<Note>>

    @Upsert
    suspend fun upsertNote(note: Note)
}