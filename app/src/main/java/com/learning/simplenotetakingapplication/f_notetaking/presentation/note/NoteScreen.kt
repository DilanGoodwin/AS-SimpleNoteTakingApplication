package com.learning.simplenotetakingapplication.f_notetaking.presentation.note

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.simplenotetakingapplication.R
import com.learning.simplenotetakingapplication.core.presentation.ViewingSystemThemes


/**
 * NoteScreen
 *
 * Stateful NoteScreen implementation that sets the state value up for the note instance.
 *
 * @param viewModel The current model instance of the NoteViewModel
 * @param modifier Passed modifier for setting padding values
 */
@Composable
fun NoteScreen(viewModel: NoteViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.state.collectAsState()

    if (state.notes.isNotEmpty() && state.initialRun) {
        viewModel.onEvent(NoteEvent.SetNoteContent(state.notes[0].content))
        viewModel.onEvent(NoteEvent.InitialLoad)
    }

    NoteScreen(state = state, onEvent = viewModel::onEvent, modifier = modifier)
}

/**
 * NoteScreen
 *
 * Stateless view of NoteScreen displaying content
 *
 * @param state The passed state of the current note
 * @param onEvent Trigger events on change, when save button is pressed and when value is changed in text field
 * @param modifier Passed modifier directly passed to column
 */
@Composable
fun NoteScreen(state: NoteState, onEvent: (NoteEvent) -> Unit, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(stringResource(R.string.enterNote), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        TextField(
            value = state.content,
            onValueChange = { onEvent(NoteEvent.SetNoteContent(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Button(modifier = Modifier.padding(10.dp), onClick = { onEvent(NoteEvent.SaveNote) }) {
            Text(stringResource(R.string.save))
        }
    }
}

@ViewingSystemThemes
@Composable
fun ViewNote() {
    NoteScreen(state = NoteState(), onEvent = {})
}