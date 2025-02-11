package com.learning.simplenotetakingapplication.f_notetaking.presentation.note

import com.learning.simplenotetakingapplication.f_notetaking.domain.Note

data class NoteState(val notes:List<Note> = emptyList(), val content:String="", val initialRun:Boolean=true)
