package com.example.nmt_history.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nmt_history.ui.theme.NMT_HistoryTheme

class ProgramNMTActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NMT_HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProgramNMTScreen(modifier = Modifier.padding(innerPadding)) {
                        finish() // повернення назад
                    }
                }
            }
        }
    }
}

@Composable
fun ProgramNMTScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    val topics = listOf(
        "Стародавня історія України",
        "Русь-Україна (Київська держава)",
        "Королівство Руське (Галицько-Волинська держава). Монгольська навала",
        "Руські удільні князівства у складі іноземних держав у XIV–XVI ст.",
        "Кримське ханство",
        "Українські землі у складі Речі Посполитої (XVI–XVII ст.)",
        "Національно-визвольна війна середини XVII ст.",
        "Козацька Україна (50–80-ті рр. XVII ст.)",
        "Українські землі наприкінці XVII – XVIII ст.",
        "Українські землі у складі Російської імперії (XVIII–XIX ст.)",
        "Українські землі у складі Австрійської імперії (XVIII–XIX ст.)",
        "Культура України XVIII – XIX ст.",
        "Українські землі у складі Російської імперії (1900–1914 рр.)",
        "Українські землі у складі Австро-Угорщини (1900–1914 рр.)",
        "Україна в роки Першої світової війни",
        "Українська революція (1917–1921)",
        "Встановлення комуністичного тоталітарного режиму",
        "Західноукраїнські землі в міжвоєнний період",
        "Україна в роки Другої світової війни",
        "Україна в перші повоєнні роки",
        "Україна в умовах десталінізації",
        "Криза радянської системи",
        "Відновлення незалежності України",
        "Становлення України як незалежної держави",
        "Творення нової України"
    )

    var checkedStates by remember { mutableStateOf(List(topics.size) { false }) }

    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        // Верхній рядок з кнопкою Назад
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = onBackClick) {
                Text("⬅️")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Програма НМТ — Чекліст тем",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(topics.indices.toList()) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedStates[index],
                        onCheckedChange = { isChecked ->
                            checkedStates = checkedStates.toMutableList().also {
                                it[index] = isChecked
                            }
                        }
                    )
                    Text(
                        text = topics[index],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgramNMTPreview() {
    NMT_HistoryTheme {
        ProgramNMTScreen(onBackClick = {})
    }
}

