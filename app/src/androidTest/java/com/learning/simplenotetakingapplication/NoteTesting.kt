package com.learning.simplenotetakingapplication

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NoteTesting{

    private val noteState:NoteState=NoteState()

    @get:Rule
    val composeTestRule=createComposeRule()

    @Before
    fun setup(){
        composeTestRule.setContent{NoteScreen(noteState,{})}
    }

    @Test
    fun checkStrings(){
        composeTestRule.onNodeWithText("Enter Note").assertExists()
        composeTestRule.onNodeWithText("Save").assertExists()
    }
}