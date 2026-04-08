package com.example.nmt_history.viewmodel

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

class ActiveTestIntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NMT_HistoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ActiveTestIntroScreen(modifier = Modifier.padding(innerPadding)) {
                        finish() // повернення назад
                    }
                }
            }
        }
    }
}

data class Question(
    val text: String,
    val options: List<String>,
    val correctIndex: Int
)

@Composable
fun ActiveTestIntroScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    val questions = listOf(
        Question(
            "Археологія — це галузь історичної науки, що вивчає:",
            listOf(
                "А. походження різних народів, їхню етнічну історію, розселення, побут і культуру.",
                "Б. архівні документи та писемні пам'ятки з метою залучення їх до наукового обігу.",
                "В. історичні та етнічні типи людини, пануючі в суспільстві системи образів і уявлень",
                "Г. Речові (матеріальні) пам'ятки та реконструює давню історію людства."
            ),
            3
        ),
        Question(
            "В основу археологічної періодизації стародавньої історії України покладено:",
            listOf(
                "А. Заняття первісної людини.",
                "Б. Природно-кліматичні умови.",
                "В. Антропологічний тип людини.",
                "Г. Матеріал виготовлення знарядь праці."
            ),
            3
        ),
        Question(
            "Галузь історичної науки, що вивчає речові пам’ятки шляхом розкопок і лабораторних досліджень:",
            listOf("А. Геральдика", "Б. Археологія", "В. Топоніміка", "Г. Історіографія"),
            1
        ),
        Question(
            "Які історичні джерела створюють найбільш повну картину господарського життя людей часів кам’яного віку?",
            listOf("А. Писемні", "Б. Речові", "В. Етнографічні", "Г. Усні"),
            1
        ),
        Question(
            "«Археологічна культура» – це:",
            listOf(
                "А. група споріднених археологічних пам’яток, поширених на певній території в межах певного історичного часу.",
                "Б. шар ґрунту, у якому під час археологічних розкопок виявлено сліди життєдіяльності людини – знаряддя праці, посуд тощо.",
                "В. Ссукупність успадкованих сучасниками від попередніх поколінь культурних цінностей – пам’ятки архітектури, мистецтва, історії.",
                "Г. система археологічної періодизації культурно-історичного розвитку, що складається з трьох віків – кам’яного, бронзового та залізного."
            ),
            0
        )
    )

    var currentIndex by remember { mutableStateOf(0) }
    var selectedIndex by remember { mutableStateOf(-1) }
    val currentQuestion = questions[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Верхній рядок з кнопкою Назад
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(onClick = onBackClick) {
                Text("⬅️ Назад")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Питання ${currentIndex + 1} з ${questions.size}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = currentQuestion.text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        currentQuestion.options.forEachIndexed { index, option ->
            Button(
                onClick = { selectedIndex = index },
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
            ) {
                Text(option)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (selectedIndex != -1) {
            if (selectedIndex == currentQuestion.correctIndex) {
                Text("✅ Правильно!", color = MaterialTheme.colorScheme.primary)
            } else {
                Text("❌ Неправильно. Спробуйте ще раз.", color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (currentIndex < questions.size - 1) {
                Button(
                    onClick = {
                        currentIndex++
                        selectedIndex = -1
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("➡️ Наступне питання")
                }
            } else {
                Text("🎉 Ви завершили тест!", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActiveTestIntroPreview() {
    NMT_HistoryTheme {
        ActiveTestIntroScreen(onBackClick = {})
    }
}

