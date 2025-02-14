package com.learning.simplenotetakingapplication.f_notetaking.data.repository

import com.learning.simplenotetakingapplication.f_notetaking.data.data_source.NoteDao
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * NoteRepositoryImplementation
 *
 * Implementation of the NoteRepository interface for accessing the data outlined in the interface
 *
 * @param dao Database Access Object
 */
class NoteRepositoryImplementation(private val dao:NoteDao):NoteRepository{
    /**
     * getNotes
     *
     * Get all the notes within the database
     *
     * @return List of Notes
     */
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    /**
     * upsertNote
     *
     * Update the note within the database using the note passed in
     *
     * @param note The note to be updated
     */
    override suspend fun upsertNote(note:Note) {
        return dao.upsertNote(note=note)
    }
}