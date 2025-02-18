package com.learning.simplenotetakingapplication.f_notetaking.data

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NoteRepositoryTestingImplementation(noteList: List<Note>) : NoteRepository {

    private var flowNotes: Flow<List<Note>> = flow { emit(noteList) }

    override fun getNotes(): Flow<List<Note>> {
        return flowNotes
    }

    override suspend fun upsertNote(note: Note) {
        flowNotes = flowNotes.map { list -> addItem(list = list, note = note) }
    }

    private fun addItem(list: List<Note>, note: Note): List<Note> {
        return list + note
    }
}