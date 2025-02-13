package com.learning.simplenotetakingapplication.di

import android.content.Context
import com.learning.simplenotetakingapplication.f_notetaking.data.data_source.NoteDB
import com.learning.simplenotetakingapplication.f_notetaking.data.repository.NoteRepositoryImplementation
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.GetNotes
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.NoteUseCases
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.UpsertNote

/**
 *
 */
interface NoteModule{
    val notesDB:NoteDB
    val noteRepository:NoteRepository
    val noteUseCases:NoteUseCases
}

class NoteModuleImplementation(context:Context):NoteModule{
    override val notesDB:NoteDB=NoteDB.getInstance(context)
    override val noteRepository:NoteRepository by lazy{NoteRepositoryImplementation(notesDB.dao)}
    override val noteUseCases:NoteUseCases by lazy{
        NoteUseCases(getNotes=GetNotes(noteRepository),upsertNote=UpsertNote(noteRepository))
    }
}