package com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.text.Text
import com.learning.simplenotetakingapplication.f_notetaking.domain.NoteEvent
import com.learning.simplenotetakingapplication.f_notetaking.presentation.note.NoteViewModel

// Stateful
@Composable
fun NoteWidgetView(viewModel:NoteViewModel){
    val state by viewModel.state.collectAsState()

    if(state.notes.isNotEmpty()&&state.initialRun){
        viewModel.onEvent(NoteEvent.SetNoteContent(state.notes[0].content))
        viewModel.onEvent(NoteEvent.InitialLoad)
    }

    NoteWidgetView(noteValue=state.content)
}

// Stateless
@Composable
fun NoteWidgetView(noteValue:String){
    Text(noteValue)
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp=200,heightDp=100)
@Composable
fun ViewNoteWidget(){
    NoteWidgetView("There is something written here")
}