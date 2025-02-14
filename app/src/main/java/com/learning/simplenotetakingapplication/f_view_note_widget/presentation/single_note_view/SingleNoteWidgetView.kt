package com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

// Stateful
@Composable
fun NoteWidgetView(viewModel:NoteWidgetViewModel){
    val state by viewModel.state.collectAsState()

    if(state.notes.isNotEmpty()){
        viewModel.onEvent(WidgetEvent.SetNoteContent(state.notes[0].content))
    }

    NoteWidgetView(noteValue=state.content)
}

// Stateless
@Composable
fun NoteWidgetView(noteValue:String){
    Scaffold(backgroundColor=GlanceTheme.colors.widgetBackground,modifier=GlanceModifier.fillMaxSize())
    {
        Box(modifier=GlanceModifier.fillMaxSize(),contentAlignment=Alignment.Center){
            Text(text=noteValue,style=TextStyle(color=GlanceTheme.colors.onSurface))
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp=200,heightDp=100)
@Composable
fun ViewNoteWidget(){
    NoteWidgetView("There is something written here")
}