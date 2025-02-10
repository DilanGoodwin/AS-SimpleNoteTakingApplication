package com.learning.simplenotetakingapplication

sealed interface NoteEvent{
    object SaveNote:NoteEvent
    object InitialLoad:NoteEvent
    data class SetNoteContent(val content:String):NoteEvent
}