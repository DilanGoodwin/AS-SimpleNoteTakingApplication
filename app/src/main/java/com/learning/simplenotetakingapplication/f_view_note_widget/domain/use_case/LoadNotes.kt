package com.learning.simplenotetakingapplication.f_view_note_widget.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_view_note_widget.domain.repository.NoteWidgetRepository
import kotlinx.coroutines.flow.Flow

/**
 * LoadNotes
 * @param repository Access object for the note repository
 */
class LoadNotes(private val repository:NoteWidgetRepository){
    /**
     * invoke
     * @return list of notes from database
     */
    operator fun invoke(): Flow<List<Note>>{
        return repository.loadNotes()
    }
}