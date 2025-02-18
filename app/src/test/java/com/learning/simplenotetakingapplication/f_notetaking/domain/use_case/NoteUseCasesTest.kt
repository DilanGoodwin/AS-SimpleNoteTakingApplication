package com.learning.simplenotetakingapplication.f_notetaking.domain.use_case

import com.learning.simplenotetakingapplication.f_notetaking.data.NoteRepositoryTestingImplementation
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
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
        )
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

    private suspend fun flattenNotesFlowToList(flowNotes: GetNotes): List<Note> {
        val flattenedList = flowNotes.invoke().flatMapConcat { flow ->
            kotlinx.coroutines.flow.flow {
                flow.forEach { emit(it) }
            }
        }
        val list = flattenedList.toList()
        return list
    }

    @Test
    fun gettingNotes() = runTest {
        val listNotes = flattenNotesFlowToList(noteUseCases.getNotes)
        assertEquals("", notes, listNotes)
    }

    @Test
    fun addingNote() = runTest {
        val newNote = Note(content = "New Note", uid = 3)
        noteUseCases.upsertNote(note = newNote)

        val listNotes = flattenNotesFlowToList(noteUseCases.getNotes)
        assertEquals("", (notes + newNote), listNotes)
    }
}