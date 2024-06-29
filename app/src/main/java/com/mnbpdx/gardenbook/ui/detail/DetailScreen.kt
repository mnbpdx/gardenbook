package com.mnbpdx.gardenbook.ui.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mnbpdx.gardenbook.ui.Destination
import com.mnbpdx.gardenbook.ui.GardenBookTopAppBar
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme

@ExperimentalMaterial3Api
@Composable
internal fun DetailScreen(
    plantName: String,
    onBackPress: () -> Unit,
) {
    BackHandler {
        onBackPress()
    }

    DetailScreenContent(
        plantName = plantName, onUpButtonPress = onBackPress
    )
}

@ExperimentalMaterial3Api
@Composable
internal fun DetailScreenContent(
    plantName: String,
    onUpButtonPress: () -> Unit,
) {
    Scaffold(
        topBar = {
            GardenBookTopAppBar(
                destination = Destination.DetailScreen(plantName), onUpButtonPress = onUpButtonPress
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = Color.Gray,
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = plantName,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DetailScreenPreview() {
    GardenBookTheme {
        DetailScreenContent(
            plantName = "Plant Name",
            onUpButtonPress = {},
        )
    }
}