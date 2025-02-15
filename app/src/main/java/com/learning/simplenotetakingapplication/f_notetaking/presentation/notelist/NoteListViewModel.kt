package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.NoteUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

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
}