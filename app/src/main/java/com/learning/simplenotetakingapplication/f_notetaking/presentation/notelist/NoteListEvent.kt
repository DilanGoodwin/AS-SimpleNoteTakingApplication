package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note

interface NoteListEvent {
    data class SetContent(val content: String) : NoteListEvent
    data class SetNote(val note: Note) : NoteListEvent
    data object SaveNote : NoteListEvent
    object ShowNoteDialog : NoteListEvent
    object HideNoteDialog : NoteListEvent
}