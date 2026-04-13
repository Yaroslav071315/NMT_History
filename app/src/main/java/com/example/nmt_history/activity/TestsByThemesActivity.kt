package com.example.nmt_history.activity

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
import androidx.compose.ui.platform.LocalContext
import com.example.nmt_history.ui.theme.NMT_HistoryTheme

class TestsByThemesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NMT_HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TestsByThemesScreen(
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = {
                            startActivity(Intent(this, TestsActivity::class.java))
                            finish()
                        },
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
fun TestsByThemesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Верхній рядок з кнопками Назад і Головний екран
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onBackClick) {
                Text("⬅️")
            }
            Button(onClick = onHomeClick) {
                Text("🏠")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Тести за темами",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                context.startActivity(Intent(context, ActiveTestIntroActivity::class.java))
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Вступ до історії України")
        }

        Button(
            onClick = { /* TODO: Navigate to Ancient Ukrainian History test */ },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("Стародавня історія України")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestsByThemesPreview() {
    NMT_HistoryTheme {
        TestsByThemesScreen(onBackClick = {}, onHomeClick = {})
    }
}
