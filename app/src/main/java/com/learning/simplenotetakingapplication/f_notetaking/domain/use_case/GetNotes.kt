package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * GetNotes
 * @param repository Access object for the note repository
 */
class GetNotes(private val repository:NoteRepository){
    /**
     * invoke
     *
     * On class call run this function
     *
     * @return All notes from the database
     */
    operator fun invoke(): Flow<List<Note>>{
        return repository.getNotes()
    }
}