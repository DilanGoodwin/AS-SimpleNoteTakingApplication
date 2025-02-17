package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun NoteDialogWindow(showDialog: Boolean, savedContent: String, onEvent: (NoteListEvent) -> Unit) {
    if (showDialog) {
        Dialog(onDismissRequest = {
            onEvent(NoteListEvent.SaveNote)
            onEvent(NoteListEvent.HideNoteDialog)
        }) {
            Box(
                modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                TextField(
                    value = savedContent,
                    onValueChange = { onEvent(NoteListEvent.SetContent(content = it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun NewNotePreview() {
    NoteDialogWindow(showDialog = true, savedContent = "", onEvent = {})
}