package com.learning.simplenotetakingapplication.f_notetaking.domain

sealed interface NoteEvent{
    object SaveNote:NoteEvent
    object InitialLoad:NoteEvent
    data class SetNoteContent(val content:String):NoteEvent
}