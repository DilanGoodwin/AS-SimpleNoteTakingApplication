package com.learning.simplenotetakingapplication.f_notetaking.data

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NoteRepositoryUiTestingImplementation(notes: List<Note>) : NoteRepository {

    private val _noteList = MutableStateFlow(notes)
    private val flowNotes: StateFlow<List<Note>> = _noteList

    override fun getNotes(): Flow<List<Note>> {
        return flowNotes
    }

    override suspend fun upsertNote(note: Note) {
        if (note.uid == null) {
            val currentList = _noteList.value.toMutableList()
            currentList.add(note)
            _noteList.value = currentList.toList()
        }
    }
}