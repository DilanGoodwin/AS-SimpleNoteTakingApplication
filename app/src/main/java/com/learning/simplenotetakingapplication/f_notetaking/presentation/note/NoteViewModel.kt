package com.learning.simplenotetakingapplication.f_notetaking.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.simplenotetakingapplication.f_notetaking.domain.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.NoteEvent
import com.learning.simplenotetakingapplication.f_notetaking.data.NoteDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteViewModel(private val dao:NoteDao):ViewModel(){
    private val _state=MutableStateFlow(NoteState())
    private val _notes=dao.getNote().stateIn(viewModelScope,SharingStarted.WhileSubscribed(),emptyList())
    val state=combine(_state,_notes){state,notes->
        state.copy(notes=notes)}.stateIn(viewModelScope,SharingStarted.WhileSubscribed(5000),NoteState())

    fun onEvent(event:NoteEvent){
        when(event){
            is NoteEvent.SetNoteContent->_state.update{it.copy(content=event.content)}
            NoteEvent.SaveNote->{
                val content=state.value.content
                if(content.isBlank()) return
                val note= Note(content)
                viewModelScope.launch{dao.upsertNote(note)}
            }
            NoteEvent.InitialLoad->_state.update{it.copy(initialRun=false)}
        }
    }
}