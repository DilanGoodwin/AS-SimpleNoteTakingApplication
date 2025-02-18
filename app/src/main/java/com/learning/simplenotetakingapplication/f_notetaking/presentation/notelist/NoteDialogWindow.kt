package com.learning.simplenotetakingapplication.f_notetaking.presentation.notelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun NoteDialogWindow(
    showDialog: Boolean,
    savedContent: String,
    onEvent: (NoteListEvent) -> Unit
) {
    if (showDialog) {
        Dialog(onDismissRequest = {
            onEvent(NoteListEvent.SaveNote)
            onEvent(NoteListEvent.HideNoteDialog)
        }) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Note")
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .clickable {
                                    onEvent(NoteListEvent.SaveNote)
                                    onEvent(NoteListEvent.HideNoteDialog)
                                }
                                .testTag(tag = TestTagCloseDialog)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = savedContent,
                        onValueChange = { onEvent(NoteListEvent.SetContent(content = it)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(tag = TestTagSelectDialogTextField)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewNotePreview() {
    NoteDialogWindow(showDialog = true, savedContent = "", onEvent = {})
}