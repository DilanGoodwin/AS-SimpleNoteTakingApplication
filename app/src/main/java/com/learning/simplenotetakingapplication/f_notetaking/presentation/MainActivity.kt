package com.learning.simplenotetakingapplication.f_notetaking.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.learning.simplenotetakingapplication.NoteApp
import com.learning.simplenotetakingapplication.f_notetaking.presentation.note.NoteScreen
import com.learning.simplenotetakingapplication.f_notetaking.presentation.note.NoteViewModel
import com.learning.simplenotetakingapplication.ui.theme.SimpleNoteTakingApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNoteTakingApplicationTheme {
                Scaffold(modifier=Modifier.fillMaxSize()){innerPadding->
                    val viewModel=NoteViewModel(NoteApp.appModule.noteUseCases)
                    NoteScreen(viewModel=viewModel,modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

