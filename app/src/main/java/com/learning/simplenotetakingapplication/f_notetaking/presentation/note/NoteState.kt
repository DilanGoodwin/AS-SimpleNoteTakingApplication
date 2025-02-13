package com.learning.simplenotetakingapplication.f_notetaking.presentation.note

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note

/**
 * NoteState
 *
 * Data class outlining the current state of the note being modified
 *
 * @param notes List of notes pulled from the database
 * @param content String value pulled from note that can be edited within the view
 * @param initialRun Has the application database been pulled from once?
 */
data class NoteState(val notes:List<Note> = emptyList(), val content:String="", val initialRun:Boolean=true)
