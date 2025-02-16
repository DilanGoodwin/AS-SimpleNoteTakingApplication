package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.learning.simplenotetakingapplication.R
import com.learning.simplenotetakingapplication.core.presentation.ViewingSystemThemes

// Stateless
@Composable
fun Note(content: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
            .clickable(onClick = onClick)
    ) {
        Text(content, maxLines = 3, modifier = Modifier.padding(5.dp))
    }
}

// Stateful
@Composable
fun ListNotes(viewModel: NoteListViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsState()

    ListNotes(state = state, onEvent = viewModel::onEvent, modifier = modifier)
    NewNote(showDialog = state.showNewNotePopup, state.newNoteContent, onEvent = viewModel::onEvent)
}

// Stateless
@Composable
fun ListNotes(
    state: NoteListState,
    onEvent: (NoteListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                modifier = Modifier.padding(5.dp),
                onClick = { onEvent(NoteListEvent.ShowNewNoteDialog) }) {
                Icon(Icons.Filled.Add, "New Note Button")
            }
        },
        modifier = modifier
    ) { contentPadding ->
        Column(modifier = modifier.padding(contentPadding)) {
            Text(
                stringResource(R.string.notes),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier.align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                items(state.notes) { note ->
                    Note(content = note.content, onClick = {})
                }
            }
        }
    }
}

@Composable
fun NewNote(showDialog: Boolean, savedContent: String, onEvent: (NoteListEvent) -> Unit) {
    if (showDialog) {
        Dialog(onDismissRequest = {
            onEvent(NoteListEvent.SaveNote)
            onEvent(NoteListEvent.HideNewNoteDialog)
        }) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                TextField(
                    value = savedContent,
                    onValueChange = { onEvent(NoteListEvent.SaveContent(content = it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }
}

@ViewingSystemThemes
@Composable
fun ViewNote() {
    Note(content = "Viewing Individual Note", onClick = {})
}

@ViewingSystemThemes
@Composable
fun ViewNoteList() {
    ListNotes(state = NoteListState(), onEvent = {}, modifier = Modifier)
}

@Preview
@Composable
fun NewNotePreview() {
    NewNote(showDialog = true, savedContent = "", onEvent = {})
}
