package com.learning.simplenotetakingapplication.di

import android.content.Context
import com.learning.simplenotetakingapplication.f_notetaking.data.data_source.NoteDB
import com.learning.simplenotetakingapplication.f_view_note_widget.data.NoteWidgetRepositoryImplementation
import com.learning.simplenotetakingapplication.f_view_note_widget.domain.repository.NoteWidgetRepository
import com.learning.simplenotetakingapplication.f_view_note_widget.domain.use_case.LoadNotes
import com.learning.simplenotetakingapplication.f_view_note_widget.domain.use_case.NoteWidgetUseCases

/**
 * NoteWidgetModule
 */
interface NoteWidgetModule{
    val notesDB:NoteDB
    val noteWidgetRepository:NoteWidgetRepository
    val noteWidgetUseCases:NoteWidgetUseCases
}

/**
 * NoteWidgetModuleImplementation
 *
 * Creation of the information needed to pull data from notes database
 *
 * @param context Application context
 */
class NoteWidgetModuleImplementation(context: Context):NoteWidgetModule{
    override val notesDB:NoteDB=NoteDB.getInstance(context)
    override val noteWidgetRepository:NoteWidgetRepository by lazy{NoteWidgetRepositoryImplementation(context=context,dao=notesDB.dao)}
    override val noteWidgetUseCases:NoteWidgetUseCases by lazy{
        NoteWidgetUseCases(loadNotes=LoadNotes(noteWidgetRepository))
    }
}