package com.mnbpdx.gardenbook.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.mnbpdx.gardenbook.R
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme

@Composable
internal fun LoadingScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box {
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
private fun LoadingText(modifier: Modifier = Modifier) {
    Text(
        text = "Garden Book",
        fontStyle = FontStyle.Italic,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
private fun PlantImage() {
    Surface {
        Image(
            painter = painterResource(
                id = R.drawable.leaf
            ),
            contentDescription = stringResource(R.string.leaf_image_content_description)
        )
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    GardenBookTheme {
        LoadingScreen()
    }
}

@Preview
@Composable
private fun PlantImagePreview() {
    PlantImage()
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    GardenBookTheme {
        LoadingText()
    }
}