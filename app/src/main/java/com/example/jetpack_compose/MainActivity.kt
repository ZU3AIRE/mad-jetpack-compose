package com.example.jetpack_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose.ui.theme.JetpackcomposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackcomposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val i = innerPadding
                    RotatingImage()
                }
            }
        }
    }
}

@Composable
fun RotatingImage() {
    var an by remember { mutableStateOf(0f) }
    var isRotating by remember { mutableStateOf(false) }
    var currentImageSource by remember { mutableStateOf(R.drawable.i1) }
    val rotation by animateFloatAsState(
        targetValue = an,
        animationSpec = tween(durationMillis = 1500), // Duration of 1 second
        finishedListener = {
            if (isRotating) {
                // Reset isRotating to false after rotation completes
                isRotating = false
            }
        }
    )

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = currentImageSource),
            contentDescription = "Rotating Image",
            modifier = Modifier
                .size(80.dp)
                .graphicsLayer(rotationZ = rotation),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (!isRotating) {
                an += 360f * 4f * listOf(1,-1).random()
                isRotating = true
                scope.launch {
                    delay(750)
                    currentImageSource = getNextDice()
                }
            }
        }, enabled = !isRotating) {
            Text(text = if (isRotating) "Rolling..." else "Roll ⚡")
        }
    }
}

fun getNextDice(): Int {
    val opts = listOf(1, 2, 3, 4, 5, 6)
    val result = when (opts.random()) {
        1 -> R.drawable.i1
        2 -> R.drawable.i2
        3 -> R.drawable.i3
        4 -> R.drawable.i4
        5 -> R.drawable.i5
        6 -> R.drawable.i6
        else -> R.drawable.img
    }
    return result
}
