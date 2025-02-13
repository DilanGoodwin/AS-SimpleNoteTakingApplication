package com.learning.simplenotetakingapplication.f_view_note_widget.presentation

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.learning.simplenotetakingapplication.NoteApp
import com.learning.simplenotetakingapplication.f_notetaking.presentation.note.NoteViewModel
import com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view.NoteWidgetView

class NoteWidget:GlanceAppWidget(){
    override suspend fun provideGlance(context:Context,id:GlanceId){
        val viewModel=NoteViewModel(NoteApp.appModule.noteUseCases)
        provideContent{NoteWidgetView(viewModel)}
    }
}