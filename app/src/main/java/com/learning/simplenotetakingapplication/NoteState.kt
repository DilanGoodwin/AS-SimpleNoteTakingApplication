package com.learning.simplenotetakingapplication

data class NoteState(val notes:List<Note> = emptyList(),val content:String="",val initialRun:Boolean=true)
