package com.learning.simplenotetakingapplication.f_notetaking.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.learning.simplenotetakingapplication.NoteApp
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.ListNotes
import com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist.NoteListViewModel
import com.learning.simplenotetakingapplication.ui.theme.SimpleNoteTakingApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNoteTakingApplicationTheme {
                val viewModel = NoteListViewModel(NoteApp.appModule.noteUseCases)
                ListNotes(viewModel = viewModel)
            }
        }
    }
}

