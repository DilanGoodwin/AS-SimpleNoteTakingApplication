package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val newNoteContent: String = "",
    val showNewNotePopup: Boolean = false
)
