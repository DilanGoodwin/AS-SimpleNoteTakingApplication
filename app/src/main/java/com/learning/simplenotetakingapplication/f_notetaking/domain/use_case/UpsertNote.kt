package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository

/**
 * UpsertNote
 * @param repository Access object for the note repository
 */
class UpsertNote(private val repository:NoteRepository){
    /**
     * invoke
     *
     * On class call run this function
     *
     * @param note The note to update the note within the database
     */
    suspend operator fun invoke(note: Note){
        repository.upsertNote(note=note)
    }
}