package com.learning.simplenotetakingapplication.f_notetaking.presentation

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.learning.simplenotetakingapplication.f_notetaking.data.NoteRepositoryUiTestingImplementation
import com.learning.simplenotetakingapplication.f_notetaking.domain.model.Note
import com.learning.simplenotetakingapplication.f_notetaking.domain.repository.NoteRepository
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.GetNotes
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.NoteUseCases
import com.learning.simplenotetakingapplication.f_notetaking.domain.use_case.UpsertNote
import com.learning.simplenotetakingapplication.f_notetaking.domain.util.SortType
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.ListNotes
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.NoteListViewModel
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.TestTagChangeSortType
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.TestTagCloseDialog
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.TestTagNewNote
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.TestTagNotesListColumns
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.TestTagSelectDialogTextField
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteListUITesting {

    private lateinit var noteUseCases: NoteUseCases
    private val notes: List<Note> = listOf(
        Note(content = "Note1", timeStamp = 1740056347421, uid = 0),
        Note(content = "Note2", timeStamp = 1740056372073, uid = 1),
        Note(content = "Note3", timeStamp = 1740056372073, uid = 2)
    )

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun addNote(content: String) {
        composeTestRule.onNodeWithTag(testTag = TestTagNewNote).performClick()
        composeTestRule.onNodeWithTag(testTag = TestTagSelectDialogTextField).performClick()
            .performTextInput(text = content)
        composeTestRule.onNodeWithTag(testTag = TestTagCloseDialog).performClick()
    }

    private fun changeSortType(sortType: SortType) {
        composeTestRule.onNodeWithTag(testTag = TestTagChangeSortType).performClick()
        composeTestRule.onNodeWithText(text = sortType.toString().lowercase()).performClick()
    }

    @Before
    fun beforeTests() {
        val noteRepo: NoteRepository = NoteRepositoryUiTestingImplementation(notes = notes)
        noteUseCases = NoteUseCases(
            getNotes = GetNotes(repository = noteRepo),
            upsertNote = UpsertNote(repository = noteRepo)
        )
        composeTestRule.setContent { ListNotes(NoteListViewModel(noteUseCases = noteUseCases)) }
    }

    /**
     * Checks content configured within the application default had been rendered in the view
     */
    @Test
    fun checkContentLoaded() = runTest {
        for (note in notes) {
            composeTestRule.onNodeWithText(note.content).assertExists()
        }
    }

    @Test
    fun addSingleNewNote() = runTest {
        val noteContent = "Creating a new note for testing"

        addNote(content = noteContent)
        composeTestRule.onNodeWithText(text = noteContent).assertExists()
    }

    @Test
    fun addMultipleNotes() {
        val moreNotes: List<String> =
            listOf("Another note 1", "Another note 2", "Another note 3", "Another note 4")

        for (content in moreNotes) {
            addNote(content = content)
            composeTestRule.onNodeWithText(content).assertExists()
        }

        checkContentLoaded()

        for (content in moreNotes) {
            composeTestRule.onNodeWithText(content).assertExists()
        }
    }

    @Test
    fun multipleNotesSameContent() {
        for (note in notes) {
            composeTestRule.onNodeWithText(note.content).assertExists()

            addNote(note.content)

            composeTestRule.onAllNodesWithText(text = note.content).assertCountEquals(2)
        }
    }

    @Test
    fun clickAwayFromDialogBox() {
        val content = "Something New"

        composeTestRule.onNodeWithTag(testTag = TestTagNewNote).performClick()

        composeTestRule.onNodeWithTag(testTag = TestTagSelectDialogTextField).performClick()
            .performTextInput(text = content)

        composeTestRule.onNodeWithText(text = notes[0].content).performClick()
    }

    @Test
    fun editNote() = runTest {
        val changedNoteText = "Something"

        composeTestRule.onNodeWithText("Note1").performClick()
        composeTestRule.onNodeWithTag(testTag = TestTagSelectDialogTextField).performClick()
            .performTextClearance()
        composeTestRule.onNodeWithTag(testTag = TestTagSelectDialogTextField).performClick()
            .performTextInput(text = changedNoteText)
        composeTestRule.onNodeWithTag(testTag = TestTagCloseDialog).performClick()
        composeTestRule.onNodeWithText(text = changedNoteText).assertExists()
    }

    @Test
    fun checkSortingNotesContent() {
        changeSortType(SortType.CONTENT)

        val sortedNotes = notes.sortedBy { it.content }

        for (i in sortedNotes.indices) {
            composeTestRule.onNodeWithTag(testTag = TestTagNotesListColumns).onChildAt(index = i)
                .assertTextContains(value = sortedNotes[i].content)
        }
    }

    @Test
    fun checkSortingNotesTimeStamp() {
        changeSortType(SortType.TIMESTAMP)

        val sortedNotes = notes.sortedBy { it.timeStamp }
        
        for (i in sortedNotes.indices) {
            composeTestRule.onNodeWithTag(testTag = TestTagNotesListColumns).onChildAt(index = i)
                .assertTextContains(value = sortedNotes[i].content)
        }
    }

    @Test
    fun checkChangingSortingNotes() {
        checkSortingNotesTimeStamp()
        checkSortingNotesContent()
    }
}