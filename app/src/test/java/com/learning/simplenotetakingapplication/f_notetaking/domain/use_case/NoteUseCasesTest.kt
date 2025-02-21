package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.data.NoteRepositoryTestingImplementation
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import com.learning.simplenotetakingapplication.f_notetaking.domain.util.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NoteUseCasesTest {

    private val notes: List<Note> =
        listOf(
            Note(content = "Note1", uid = 0),
            Note(content = "Note2", uid = 1),
            Note(content = "Note3", uid = 2)
        ).sortedBy { it.content }
    private lateinit var noteUseCases: NoteUseCases

    @Before
    fun beforeTests() {
        val noteRepo: NoteRepository = NoteRepositoryTestingImplementation(noteList = notes)
        noteUseCases =
            NoteUseCases(
                getNotes = GetNotes(repository = noteRepo),
                upsertNote = UpsertNote(repository = noteRepo)
            )
    }

    private suspend fun flattenNotesFlowToList(flowNotes: Flow<List<Note>>): List<Note> {
        val flattenedList = flowNotes.flatMapConcat { flow ->
            kotlinx.coroutines.flow.flow {
                flow.forEach { emit(it) }
            }
        }
        val list = flattenedList.toList()
        return list
    }

    private fun addingItems(newNote: Note): List<Note> {
        return (notes + newNote).sortedBy { it.uid }
    }

    @Test
    fun gettingNotes() = runTest {
        val listNotes = flattenNotesFlowToList(noteUseCases.getNotes())
        assertEquals("", notes, listNotes)
    }

    @Test
    fun gettingNotesOrderedId() = runTest {
        val listNotes = flattenNotesFlowToList(noteUseCases.getNotes(sortType = SortType.ID))
        assertEquals("", notes.sortedBy { it.uid }, listNotes)
    }

    @Test
    fun gettingNotesOrderedContent() = runTest {
        val listNotes = flattenNotesFlowToList(noteUseCases.getNotes(sortType = SortType.CONTENT))
        assertEquals("", notes.sortedBy { it.content }, listNotes)
    }

    @Test
    fun gettingNotesOrderedTimeStamp() = runTest {
        val listNotes =
            flattenNotesFlowToList(noteUseCases.getNotes(sortType = SortType.CREATION_TIME))
        assertEquals("", notes.sortedBy { it.creationTime }, listNotes)
    }

    @Test
    fun addingNote() = runTest {
        val newNote = Note(content = "New Note", uid = 3)
        noteUseCases.upsertNote(note = newNote)

        val listNotes = flattenNotesFlowToList(noteUseCases.getNotes())
        assertEquals("", addingItems(newNote = newNote), listNotes)
    }
}