package com.mnbpdx.gardenbook.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mnbpdx.gardenbook.R
import com.mnbpdx.gardenbook.model.plantNames
import com.mnbpdx.gardenbook.ui.Destination
import com.mnbpdx.gardenbook.ui.GardenBookBottomAppBar
import com.mnbpdx.gardenbook.ui.GardenBookTopAppBar
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme

@ExperimentalMaterial3Api
@Composable
internal fun HomeScreen(
//    viewModel: HomeScreenViewModel,
    onPlantCardPress: (String) -> Unit,
) {
    HomeScreenContent(
        onPlantCardPress = onPlantCardPress
    )
}

@ExperimentalMaterial3Api
@Composable
internal fun HomeScreenContent(
    onPlantCardPress: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            GardenBookTopAppBar(
                destination = Destination.HomeScreen,
                onArrowBackPress = { }
            )
        },
        bottomBar = { GardenBookBottomAppBar(isSelected = true) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(plantNames) { plantName ->
                    Spacer(modifier = Modifier.height(16.dp))
                    PlantCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        name = plantName,
                        onPress = onPlantCardPress,
                    )
                }
            }
        }
    }
}

// THE definition of PlantCard
@Composable
private fun PlantCard(
    modifier: Modifier = Modifier,
    name: String,
    onPress: (String) -> Unit,
) {
    Card(
        modifier = modifier.clickable { onPress(name) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 16.dp
        )
    ) {
        Surface { // TODO: duplicate surface, consider removing
            Row {
                Image(
                    modifier = Modifier.weight(.33f),
                    painter = painterResource(R.drawable.leaf),
                    contentDescription = stringResource(R.string.leaf_image_content_description)
                )
                Box(
                    modifier = Modifier
                        .weight(.66f)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = name,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PlantCardPreview() {
    GardenBookTheme {
        PlantCard(
            name = "pothos",
            onPress = { }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
private fun HomeScreenPreview() {
    GardenBookTheme {
        HomeScreenContent(onPlantCardPress = { })
    }
}