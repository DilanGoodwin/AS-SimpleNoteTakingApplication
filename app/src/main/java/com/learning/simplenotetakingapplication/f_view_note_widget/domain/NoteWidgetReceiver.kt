package com.learning.simplenotetakingapplication.f_view_note_widget.domain

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.learning.simplenotetakingapplication.f_view_note_widget.presentation.NoteWidget

/**
 * NoteWidgetReceiver
 * @property glanceAppWidget
 */
class NoteWidgetReceiver() :GlanceAppWidgetReceiver() {
    override val glanceAppWidget:GlanceAppWidget=NoteWidget()
}