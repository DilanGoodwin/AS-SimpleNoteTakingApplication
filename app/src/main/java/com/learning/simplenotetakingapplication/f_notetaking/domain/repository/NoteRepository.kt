package com.learning.simplenotetakingapplication.f_notetaking.domain.repository

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * NoteRepository
 */
interface NoteRepository{
    fun getNotes(): Flow<List<Note>>
    suspend fun upsertNote(note: Note)
}