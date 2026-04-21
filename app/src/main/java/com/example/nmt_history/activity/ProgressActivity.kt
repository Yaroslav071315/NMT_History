package com.example.nmt_history.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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


class ProgressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NMT_HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProgressScreen(modifier = Modifier.padding(innerPadding)) {
                        finish() // повернення назад
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    val calendar = java.util.Calendar.getInstance()
    val today = calendar.timeInMillis

    val firstLaunch = java.util.GregorianCalendar(2026, 3, 1).timeInMillis // 1 квітня
    val daysVisited = ((today - firstLaunch) / (1000 * 60 * 60 * 24)).toInt() + 1

    val examDate = java.util.GregorianCalendar(2026, 6, 1).timeInMillis // 1 липня
    val daysLeft = ((examDate - today) / (1000 * 60 * 60 * 24)).toInt()

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Верхній рядок з кнопками Назад і Меню
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onBackClick) {
                Text("⬅️ Назад")
            }

            Box {
                Button(onClick = { expanded = true }) {
                    Text("☰ Меню")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("🏠 Головний екран") },
                        onClick = {
                            context.startActivity(Intent(context, MainActivity::class.java))
                            (context as? ComponentActivity)?.finish()
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("📝 Тести") },
                        onClick = {
                            context.startActivity(Intent(context, TestsActivity::class.java))
                            (context as? ComponentActivity)?.finish()
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("📖 Програма НМТ") },
                        onClick = {
                            context.startActivity(Intent(context, ProgramNMTActivity::class.java))
                            (context as? ComponentActivity)?.finish()
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "📊 Прогрес",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Text(
            text = "Ви заходили у додаток $daysVisited днів",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "До НМТ залишилось $daysLeft днів",
                    Toast.LENGTH_LONG
                ).show()
            },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("📅 Скільки днів лишилось до НМТ")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressPreview() {
    NMT_HistoryTheme {
        ProgressScreen(onBackClick = {})
    }
}