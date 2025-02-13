package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository

class UpsertNote(private val repository:NoteRepository){
    suspend operator fun invoke(note: Note){
        repository.upsertNote(note=note)
    }
}