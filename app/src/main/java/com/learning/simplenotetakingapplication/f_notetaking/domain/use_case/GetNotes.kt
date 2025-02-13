package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes(private val repository:NoteRepository){
    operator fun invoke(): Flow<List<Note>>{
        return repository.getNotes()
    }
}