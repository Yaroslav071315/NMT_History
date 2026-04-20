package com.example.nmt_history.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nmt_history.ui.theme.NMT_HistoryTheme

class TestsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NMT_HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TestsScreen(
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = { finish() },
                        onHomeClick = {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TestsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Верхній рядок з кнопками Назад і Меню
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onBackClick) {
                Text("⬅️")
            }

            Box {
                Button(onClick = { expanded = true }) {
                    Text("☰")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("🏠 Головний екран") },
                        onClick = {
                            onHomeClick()
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("📝 Тести") },
                        onClick = { expanded = false } // вже тут
                    )
                    DropdownMenuItem(
                        text = { Text("📚 Матеріали") },
                        onClick = { expanded = false /* TODO */ }
                    )
                    DropdownMenuItem(
                        text = { Text("📖 Програма НМТ") },
                        onClick = {
                            context.startActivity(Intent(context, ProgramNMTActivity::class.java))
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("📊 Прогрес") },
                        onClick = { expanded = false /* TODO */ }
                    )
                    DropdownMenuItem(
                        text = { Text("🏛 Пам’ятки та персоналії") },
                        onClick = { expanded = false /* TODO */ }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "📝 Тести",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, TestsByThemesActivity::class.java))
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Тести за темами")
        }

        Button(
            onClick = { /* TODO: Navigate to full timed test */ },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Повний тест на час")
        }

        Button(
            onClick = { /* TODO: Navigate to random questions */ },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Випадкові питання")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestsScreenPreview() {
    NMT_HistoryTheme {
        TestsScreen(onBackClick = {}, onHomeClick = {})
    }
}
