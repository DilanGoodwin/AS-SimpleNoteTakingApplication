package com.learning.simplenotetakingapplication.f_notetaking.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.NoteUseCases
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * NoteViewModel
 *
 * When a note event is triggered mapped to what should occur
 *
 * @param noteUseCases Database access object for the database pulling information from
 * @property state Combined values of state (NoteState class) and notes (notes from database)
 */
class NoteViewModel(private val noteUseCases:NoteUseCases):ViewModel(){
    private val _state=MutableStateFlow(NoteState())
    private val _notes=noteUseCases.getNotes().stateIn(viewModelScope,SharingStarted.WhileSubscribed(),emptyList())
    val state=combine(_state,_notes){state,notes->
        state.copy(notes=notes)}.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),NoteState())

    /**
     * onEvent
     *
     * When event triggered by view match that to the corresponding response
     *
     * @param event Sealed interface object call
     */
    fun onEvent(event:NoteEvent){
        when(event){
            NoteEvent.InitialLoad->_state.update{it.copy(initialRun=false)}
            NoteEvent.SaveNote->{
                val content=state.value.content
                if(content.isBlank()) return
                val note=Note(content)
                viewModelScope.launch{noteUseCases.upsertNote(note)}
            }
            is NoteEvent.SetNoteContent->_state.update{it.copy(content=event.content)}
        }
    }
}