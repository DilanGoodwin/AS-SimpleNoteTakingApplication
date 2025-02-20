package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.util.SortType

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val currentNote: Note = Note(),
    val newNoteContent: String = "",
    val sortType: SortType = SortType.CONTENT,
    val showNoteDialog: Boolean = false,
    val showDropDownMenu: Boolean = false
)
