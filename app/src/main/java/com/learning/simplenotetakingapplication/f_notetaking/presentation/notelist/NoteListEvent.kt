package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.util.SortType

interface NoteListEvent {
    data class SetContent(val content: String) : NoteListEvent
    data class SetNote(val note: Note) : NoteListEvent
    data class UpdateSortType(val sortType: SortType) : NoteListEvent
    data object SaveNote : NoteListEvent
    object ShowNoteDialog : NoteListEvent
    object HideNoteDialog : NoteListEvent
    object ShowOrderDropDownMenu : NoteListEvent
    object HideOrderDropDownMenu : NoteListEvent
}