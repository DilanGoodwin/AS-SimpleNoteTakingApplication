package com.learning.simplenotetakingapplication.f_notetaking.presentation.note


/**
 * NoteEvent
 *
 * Interface for the different functions
 */
sealed interface NoteEvent{
    data object SaveNote:NoteEvent
    data object InitialLoad:NoteEvent
    data class SetNoteContent(val content:String):NoteEvent
}