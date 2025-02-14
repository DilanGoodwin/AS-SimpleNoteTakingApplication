package com.learning.simplenotetakingapplication.f_view_note_widget.presentation

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.learning.simplenotetakingapplication.NoteApp
import com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view.NoteWidgetView
import com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view.NoteWidgetViewModel

/**
 * NoteWidget
 */
class NoteWidget:GlanceAppWidget(){
    /**
     * provideGlance
     */
    override suspend fun provideGlance(context:Context,id:GlanceId){
        val viewModel= NoteWidgetViewModel(NoteApp.widgetModule.noteWidgetUseCases)
        provideContent{NoteWidgetView(viewModel)}
    }
}