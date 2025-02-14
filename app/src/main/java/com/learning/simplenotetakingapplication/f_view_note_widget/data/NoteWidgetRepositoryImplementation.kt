package com.learning.simplenotetakingapplication.f_view_note_widget.data

import android.content.Context
import androidx.glance.appwidget.updateAll
import com.learning.simplenotetakingapplication.f_notetaking.data.data_source.NoteDao
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_view_note_widget.domain.repository.NoteWidgetRepository
import com.learning.simplenotetakingapplication.f_view_note_widget.presentation.NoteWidget
import kotlinx.coroutines.flow.Flow

/**
 * NoteWidgetRepositoryImplementation
 *
 * Implementation of the NoteWidgetRepository interface for accessing the data outlined in the interface.
 * Context is passed to update the widget when new information is passed to the database
 *
 * @param context Application context
 * @param dao Database Access Object
 */
class NoteWidgetRepositoryImplementation(private val context:Context,private val dao:NoteDao):NoteWidgetRepository {
    override suspend fun noteUpdated() {
        NoteWidget().updateAll(context=context)
    }

    override fun loadNotes():Flow<List<Note>> {
        return dao.getNotes()
    }
}