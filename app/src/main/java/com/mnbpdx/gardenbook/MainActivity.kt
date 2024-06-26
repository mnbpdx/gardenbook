package com.mnbpdx.gardenbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Composables go here -->
            // Composables are functions, but start with capital
            // letters, like Greeting, below.
            GardenBookTheme {
                LoadingScreen()
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadingText() // <-- Greeting's callsite
                PlantImage()
            }
        }
    }
}

@Composable
fun PlantImage() {
   Surface {
        Image(
            painter = painterResource(
                id = R.drawable.leaf
            ),
            contentDescription = "leaf icon"
        )
    }
}


// Greeting's definition
@Composable
fun LoadingText(modifier: Modifier = Modifier) {
    Text(
        text = "Garden Book",
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}


// Previews

@Preview
@Composable
fun GardenBookScaffoldPreview() {
    GardenBookTheme {
        LoadingScreen()
    }
}

@Preview
@Composable
fun PlantImagePreview() {
    PlantImage()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GardenBookTheme {
        LoadingText()
    }
}