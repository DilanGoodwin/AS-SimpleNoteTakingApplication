package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.NoteUseCases
import com.learning.simplenotetakingapplication.f_notetaking.domain.util.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
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
    private val _sortType = MutableStateFlow(SortType.CONTENT)
    private val _notes =
        _sortType.flatMapLatest { sortType -> noteUseCases.getNotes(sortType = sortType) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _sortType, _notes) { state, sortType, notes ->
        state.copy(
            notes = notes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    fun onEvent(event: NoteListEvent) {
        when (event) {
            is NoteListEvent.SetContent -> _state.update { it.copy(newNoteContent = event.content) }
            is NoteListEvent.SetNote -> {
                _state.update { it.copy(currentNote = event.note) }
                onEvent(NoteListEvent.SetContent(content = event.note.content))
            }

            is NoteListEvent.UpdateSortType -> _sortType.value = event.sortType
            NoteListEvent.SaveNote -> {
                if (_state.value.newNoteContent.isBlank()) return
                _state.value.currentNote.content = _state.value.newNoteContent
                _state.value.currentNote.timeStamp = System.currentTimeMillis()
                viewModelScope.launch { noteUseCases.upsertNote(_state.value.currentNote) }
                onEvent(NoteListEvent.SetNote(note = Note()))
            }

            is NoteListEvent.ShowNoteDialog -> _state.update { it.copy(showNoteDialog = true) }
            is NoteListEvent.HideNoteDialog -> _state.update { it.copy(showNoteDialog = false) }
            is NoteListEvent.ShowOrderDropDownMenu -> _state.update { it.copy(showDropDownMenu = true) }
            is NoteListEvent.HideOrderDropDownMenu -> _state.update { it.copy(showDropDownMenu = false) }
        }
    }
}