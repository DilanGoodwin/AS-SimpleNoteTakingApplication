package com.learning.simplenotetakingapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.learning.simplenotetakingapplication.ui.theme.SimpleNoteTakingApplicationTheme

class MainActivity : ComponentActivity() {

    private val db by lazy{Room.databaseBuilder(applicationContext,NoteDB::class.java,"note.db").build()}
    private val viewModel by viewModels<NoteViewModel>(factoryProducer={
        object:ViewModelProvider.Factory{override fun <T:ViewModel>create(modelClass:Class<T>):T{
            return NoteViewModel(db.dao) as T
        }}
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNoteTakingApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){innerPadding->
                    val state by viewModel.state.collectAsState()
                    NoteScreen(state=state,onEvent=viewModel::onEvent,modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NoteScreen(state:NoteState,onEvent:(NoteEvent)->Unit,modifier:Modifier=Modifier){
    if(state.notes.isNotEmpty()&&state.initialRun){
        onEvent(NoteEvent.SetNoteContent(state.notes[0].content))
        onEvent(NoteEvent.InitialLoad)
    }

    Column(horizontalAlignment=Alignment.CenterHorizontally,modifier=modifier){
        Text(stringResource(R.string.enterNote),fontWeight=FontWeight.Bold,fontSize=20.sp)
        TextField(value=state.content,onValueChange={onEvent(NoteEvent.SetNoteContent(it))},modifier=Modifier.fillMaxWidth().padding(10.dp))
        Button(modifier=Modifier.padding(10.dp),onClick={onEvent(NoteEvent.SaveNote)}){Text(stringResource(R.string.save))}
    }
}