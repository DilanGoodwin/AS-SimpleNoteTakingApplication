package com.learning.simplenotetakingapplication.f_view_note_widget.presentation.single_note_view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.simplenotetakingapplication.f_view_note_widget.domain.use_case.NoteWidgetUseCases
import kotlinx.coroutines.flow.*

/**
 * NoteWidgetViewModel
 * @param noteWidgetUseCases
 * @property state
 */
class NoteWidgetViewModel(private val noteWidgetUseCases:NoteWidgetUseCases):ViewModel(){
    private val _state=MutableStateFlow(NoteWidgetState())
    private val _notes=noteWidgetUseCases.loadNotes().stateIn(viewModelScope,SharingStarted.WhileSubscribed(),emptyList())
    val state=combine(_state,_notes){state,notes->
        state.copy(notes=notes)}.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),NoteWidgetState())

    /**
     * onEvent
     * @param event
     */
    fun onEvent(event:WidgetEvent){
        when(event){
            is WidgetEvent.SetNoteContent->_state.update{it.copy(content=event.content)}
        }
    }
}