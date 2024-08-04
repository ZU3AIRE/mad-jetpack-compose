package com.example.jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose.ui.theme.JetpackcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiscoBar(
                        name = "Zubair Jamil",
                        cgpa = 3.24,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val colors = listOf(
    Color.Red,
    Color.Blue,
    Color.LightGray,
    Color.Green,
    Color.Magenta,
    Color.Cyan,
    Color.Yellow
)

@Composable
fun DiscoBar(name: String, cgpa: Number, modifier: Modifier) {
    var color = remember { mutableStateOf(Color.Red) }

    Column(
        modifier = Modifier
            .background(color.value)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                color.value = colors.random()
            }
        ) {
            Text(text = "Do some magic")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackcomposeTheme {
        DiscoBar("Zubair Jamil", 3.24, Modifier.padding(PaddingValues(16.dp)))

    }
}