package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import com.learning.simplenotetakingapplication.f_notetaking.domain.util.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * GetNotes
 * @param repository Access object for the note repository
 */
class GetNotes(private val repository: NoteRepository) {
    /**
     * invoke
     *
     * On class call run this function
     *
     * @param sortType the ordering the notes should be formatted to. By default set to content
     * @return All notes from the database
     */
    operator fun invoke(sortType: SortType = SortType.ID): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when (sortType) {
                SortType.ID -> notes.sortedBy { it.uid }
                SortType.CONTENT -> notes.sortedBy { it.content.lowercase() }
                SortType.TIMESTAMP -> notes.sortedBy { it.timeStamp }
            }
        }
    }
}