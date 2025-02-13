package com.learning.simplenotetakingapplication.f_notetaking.data.repository

import com.learning.simplenotetakingapplication.f_notetaking.data.data_source.NoteDao
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImplementation(private val dao:NoteDao):NoteRepository{
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun upsertNote(note:Note) {
        return dao.upsertNote(note=note)
    }
}