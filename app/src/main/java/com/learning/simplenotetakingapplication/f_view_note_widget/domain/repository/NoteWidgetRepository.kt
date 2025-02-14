package com.learning.simplenotetakingapplication.f_view_note_widget.domain.repository

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * NoteWidgetRepository
 */
interface NoteWidgetRepository{
    suspend fun noteUpdated()
    fun loadNotes(): Flow<List<Note>>
}