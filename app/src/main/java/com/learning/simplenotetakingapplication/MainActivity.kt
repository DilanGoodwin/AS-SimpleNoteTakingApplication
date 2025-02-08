package com.learning.simplenotetakingapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.simplenotetakingapplication.ui.theme.SimpleNoteTakingApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNoteTakingApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()){innerPadding->
                    TextArea(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TextArea(modifier:Modifier=Modifier){
    var text by remember{mutableStateOf("")}

    Column(horizontalAlignment=Alignment.CenterHorizontally,modifier=modifier){
        Text("Enter Note",fontWeight=FontWeight.Bold,fontSize=20.sp)
        TextField(value=text,onValueChange={text=it},modifier=Modifier.fillMaxWidth().padding(10.dp))
    }
}

@Preview(name="LightMode",showBackground = true)
@Preview(name="DarkMode",uiMode= Configuration.UI_MODE_NIGHT_YES,showBackground=true)
annotation class SmallAreaPreview

@Preview(name="LightModeFull",uiMode=Configuration.UI_MODE_NIGHT_NO,showSystemUi=true)
@Preview(name="DarkModeFull",uiMode=Configuration.UI_MODE_NIGHT_YES,showSystemUi=true)
annotation class FullAreaPreview

@SmallAreaPreview
@Composable
fun GreetingPreview() {
    SimpleNoteTakingApplicationTheme {
        TextArea()
    }
}

@FullAreaPreview
@Composable
fun FullNotePreview() {
    SimpleNoteTakingApplicationTheme {
        Scaffold(modifier = Modifier.fillMaxSize()){innerPadding->
            TextArea(modifier=Modifier.padding(innerPadding))
        }
    }
}