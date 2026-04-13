package com.example.nmt_history.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nmt_history.ui.theme.NMT_HistoryTheme
import kotlin.random.Random
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration

import androidx.compose.runtime.Composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NMT_HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val quotes = listOf(
        "Пам’ятайте чому ви почали – Ерін Янус",
        "Успіх приходить до тих, хто працює",
        "Кожен день — нова можливість",
        "Знання — це сила – Френсіс Бекон"
    )
    val randomQuote = remember { quotes[Random.nextInt(quotes.size)] }
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        // Горизонтальна орієнтація → всі кнопки в ряд
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Вітаємо в додатку\nНМТ Історія",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Text(
                    text = randomQuote,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Button(
                    onClick = { context.startActivity(Intent(context, TestsActivity::class.java)) },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) { Text("📝 Тести") }

                Button(
                    onClick = { /* TODO: Navigate to Materials */ },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) { Text("📚 Матеріали") }

                Button(
                    onClick = { context.startActivity(Intent(context, ProgramNMTActivity::class.java)) },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) { Text("📖 Програма НМТ") }

                Button(
                    onClick = { /* TODO: Navigate to Progress */ },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) { Text("📊 Прогрес") }

                Button(
                    onClick = { /* TODO: Navigate to Monuments */ },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                ) { Text("🏛 Пам’ятки та персоналії") }
            }
        }
    } else {
        // Портретна орієнтація → кнопки одна під одною
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Вітаємо в додатку\nНМТ Історія",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = randomQuote,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = { context.startActivity(Intent(context, TestsActivity::class.java)) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) { Text("📝 Тести") }

            Button(
                onClick = { /* TODO: Navigate to Materials */ },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) { Text("📚 Матеріали") }

            Button(
                onClick = { context.startActivity(Intent(context, ProgramNMTActivity::class.java)) },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) { Text("📖 Програма НМТ") }

            Button(
                onClick = { /* TODO: Navigate to Progress */ },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) { Text("📊 Прогрес") }

            Button(
                onClick = { /* TODO: Navigate to Monuments */ },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            ) { Text("🏛 Пам’ятки та персоналії") }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NMT_HistoryTheme {
        MainScreen()
    }
}
