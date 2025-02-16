package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

interface NoteListEvent {
    data class SaveContent(val content: String) : NoteListEvent
    data object SaveNote : NoteListEvent
    object ShowNewNoteDialog : NoteListEvent
    object HideNewNoteDialog : NoteListEvent
}