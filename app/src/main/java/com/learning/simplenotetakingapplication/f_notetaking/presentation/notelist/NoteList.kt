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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.simplenotetakingapplication.R
import com.learning.simplenotetakingapplication.core.presentation.ViewingSystemThemes
import com.learning.simplenotetakingapplication.f_notetaking.domain.util.SortType

// Stateful
@Composable
fun ListNotes(viewModel: NoteListViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsState()

    NoteDialogWindow(
        showDialog = state.showNoteDialog,
        savedContent = state.newNoteContent,
        onEvent = viewModel::onEvent
    )
    ListNotes(
        state = state,
        onEvent = viewModel::onEvent,
        modifier = modifier
    )
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
                modifier = Modifier
                    .padding(5.dp)
                    .testTag(TestTagNewNote),
                onClick = { onEvent(NoteListEvent.ShowNoteDialog) }
            ) {
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

            Box {
                IconButton(
                    onClick = { onEvent(NoteListEvent.ShowOrderDropDownMenu) },
                    modifier = Modifier.testTag(TestTagChangeSortType)
                ) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                    OrderDropDownMenu(state = state, onEvent = onEvent)
                }
            }

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxSize()
                    .padding(5.dp)
                    .testTag(TestTagNotesListColumns)
            ) {
                items(state.notes) { note ->
                    Note(content = note.content, onClick = {
                        onEvent(NoteListEvent.SetNote(note = note))
                        onEvent(NoteListEvent.ShowNoteDialog)
                    })
                }
            }
        }
    }
}

// Stateless
@Composable
fun OrderDropDownMenu(state: NoteListState, onEvent: (NoteListEvent) -> Unit) {
    Box {
        DropdownMenu(
            expanded = state.showDropDownMenu,
            onDismissRequest = { onEvent(NoteListEvent.HideOrderDropDownMenu) }) {
            for (type in SortType.entries) {
                DropdownMenuItem(
                    text = { Text(text = type.toString().lowercase()) },
                    onClick = { onEvent(NoteListEvent.UpdateSortType(sortType = type)) })
            }
        }
    }
}

// Stateless
@Composable
fun Note(content: String, onClick: () -> Unit) {
    val lineLength = 3

    Box(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
            .clickable(onClick = onClick)
    ) {
        Text(
            content,
            minLines = lineLength,
            maxLines = lineLength,
            modifier = Modifier.padding(5.dp)
        )
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