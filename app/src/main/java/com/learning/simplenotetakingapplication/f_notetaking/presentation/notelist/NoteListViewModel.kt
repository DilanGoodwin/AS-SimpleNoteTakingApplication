package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.NoteUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * NoteListViewModel
 *
 * @param noteUseCases
 * @property state
 */
class NoteListViewModel(private val noteUseCases: NoteUseCases) : ViewModel() {
    private val _state = MutableStateFlow(NoteListState())
    private val _notes = noteUseCases.getNotes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _notes) { state, notes ->
        state.copy(notes = notes)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    fun onEvent(event: NoteListEvent) {
        when (event) {
            NoteListEvent.SaveNote -> {
                if (_state.value.newNoteContent.isBlank()) return
                viewModelScope.launch { noteUseCases.upsertNote(Note(_state.value.newNoteContent)) }
                _state.update { it.copy(newNoteContent = "") }
            }

            is NoteListEvent.ShowNewNoteDialog -> _state.update { it.copy(showNewNotePopup = true) }
            is NoteListEvent.HideNewNoteDialog -> _state.update { it.copy(showNewNotePopup = false) }
            is NoteListEvent.SaveContent -> _state.update { it.copy(newNoteContent = event.content) }
        }
    }
}