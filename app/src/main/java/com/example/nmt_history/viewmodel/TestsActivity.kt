package com.example.nmt_history.viewmodel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nmt_history.ui.theme.NMT_HistoryTheme
import androidx.compose.ui.platform.LocalContext
import com.example.nmt_history.viewmodel.TestsByThemesActivity



class TestsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NMT_HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TestsScreen(
                        modifier = Modifier.padding(innerPadding),
                        onHomeClick = {
                            // повернення на головний екран
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
fun TestsScreen(modifier: Modifier = Modifier, onHomeClick: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // верхній рядок з кнопкою 🏠 справа
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onHomeClick) {
                Text("🏠")
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
        TestsScreen(onHomeClick = {})
    }
}
