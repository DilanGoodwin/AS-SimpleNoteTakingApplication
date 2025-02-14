package com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note

/**
 * NoteWidgetState
 * @param notes List of notes from database
 * @param content The current value of the note
 */
data class NoteWidgetState(val notes:List<Note> = emptyList(),val content:String="")