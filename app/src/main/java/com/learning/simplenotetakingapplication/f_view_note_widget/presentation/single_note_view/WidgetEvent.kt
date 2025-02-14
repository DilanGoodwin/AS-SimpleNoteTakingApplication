package com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view

interface WidgetEvent{
    data class SetNoteContent(val content:String):WidgetEvent
}