package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aesthetictodo.ui.theme.AestheticTodoTheme
import com.example.aesthetictodo.ui.theme.AestheticBackground
import com.example.aesthetictodo.ui.theme.CocoaText
import com.example.aesthetictodo.ui.theme.PinkPrimary
import com.example.aesthetictodo.ui.theme.CardWhite

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState: Bundle?)
        setContent {
            AestheticTodoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = AestheticBackground
                ) {
                    TodoScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen() {
    // This holds your current text input
    var taskText by remember { mutableStateOf("") }

    // This reactive list tracks your added tasks dynamically
    val todoList = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Aesthetic Header Title
        Text(
            text = "my cozy day. ✨",
            fontSize = 32.sp,
            fontWeight = FontWeight.Medium,
            color = CocoaText,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )

        // Input Layout Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = taskText,
                onValueChange = { taskText = it },
                placeholder = { Text("Add a sweet plan...", color = CocoaText.copy(alpha = 0.5f)) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = CardWhite,
                    focusedIndicatorColor = PinkPrimary,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
                )
            )

            Button(
                onClick = {
                    if (taskText.isNotBlank()) {
                        todoList.add(taskText) // Adds the typed item to your list
                        taskText = ""          // Clears the input field
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary)
            ) {
                Text("Add", color = CocoaText, fontWeight = FontWeight.Medium)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Dynamic List View (Renders your added tasks beautifully)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(todoList) { task ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Small pink bullet indicator
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(PinkPrimary, shape = RoundedCornerShape(6.dp))
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = task,
                            fontSize = 16.sp,
                            color = CocoaText
                        )
                    }
                }
            }
        }
    }
}