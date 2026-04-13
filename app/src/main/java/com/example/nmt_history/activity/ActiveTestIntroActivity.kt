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
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration

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

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        // Горизонтальна орієнтація → питання ліворуч, варіанти та кнопки праворуч
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(1f).padding(end = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Button(onClick = onBackClick) { Text("⬅️") }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Питання ${currentIndex + 1} з ${questions.size}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(currentQuestion.text, style = MaterialTheme.typography.headlineSmall)
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        Text("❌ Неправильно", color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    if (currentIndex < questions.size - 1) {
                        Button(onClick = {
                            currentIndex++
                            selectedIndex = -1
                        }) {
                            Text("➡️ Наступне питання")
                        }
                    } else {
                        Text("🎉 Ви завершили тест!")
                    }
                }
            }
        }
    } else {
        // Портретна орієнтація → все в колонку
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Button(onClick = onBackClick) { Text("⬅️") }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Питання ${currentIndex + 1} з ${questions.size}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(currentQuestion.text, style = MaterialTheme.typography.headlineSmall)

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
                    Text("❌ Неправильно", color = MaterialTheme.colorScheme.error)
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (currentIndex < questions.size - 1) {
                    Button(onClick = {
                        currentIndex++
                        selectedIndex = -1
                    }) {
                        Text("➡️ Наступне питання")
                    }
                } else {
                    Text("🎉 Ви завершили тест!")
                }
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

