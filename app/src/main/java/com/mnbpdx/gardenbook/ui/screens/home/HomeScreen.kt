package com.mnbpdx.gardenbook.ui.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mnbpdx.gardenbook.R
import com.mnbpdx.gardenbook.model.Plant
import com.mnbpdx.gardenbook.model.plantList
import com.mnbpdx.gardenbook.ui.Destination
import com.mnbpdx.gardenbook.ui.GardenBookBottomAppBar
import com.mnbpdx.gardenbook.ui.GardenBookTopAppBar
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme
import androidx.lifecycle.viewmodel.compose.viewModel


@ExperimentalMaterial3Api
@Composable
internal fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel(),
    onPlantCardPress: (Int) -> Unit,
    onAddPlantPress: () -> Unit,
) {
    HomeScreenContent(
        onPlantCardPress = onPlantCardPress,
        onAddPlantPress = onAddPlantPress,
        plants = viewModel.plants.collectAsState().value,
    )
}

@ExperimentalMaterial3Api
@Composable
internal fun HomeScreenContent(
    onPlantCardPress: (Int) -> Unit,
    onAddPlantPress: () -> Unit,
    plants: List<Plant>
) {
    Scaffold(
        topBar = {
            GardenBookTopAppBar(
                destination = Destination.HomeScreen
            )
        },
        bottomBar = { GardenBookBottomAppBar(isSelected = true) },
        floatingActionButton = { AddPlantFAB(
            onPress = onAddPlantPress,
        ) },
        floatingActionButtonPosition = FabPosition.End
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
                items(plants) { plant ->
                    Spacer(modifier = Modifier.height(16.dp))
                    PlantCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        name = plant.name,

                        onPress = onPlantCardPress,
                        id = plant.id,
                    )
                }
            }
        }
    }
}

@Composable
private fun AddPlantFAB(
    onPress: () -> Unit
) {
    Button(
        modifier = Modifier.size(64.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = onPress,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors()
            .copy(containerColor = MaterialTheme.colorScheme.onPrimary),
    ) {
        Icon(
            modifier = Modifier.size(128.dp),
            tint = MaterialTheme.colorScheme.primary,
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_icon_content_description)
        )
    }
}

// THE definition of PlantCard
@Composable
private fun PlantCard(
    modifier: Modifier = Modifier,
    onPress: (Int) -> Unit,
    name: String,
    id: Int,
) {
    Card(
        modifier = modifier.clickable { onPress(id) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
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
            onPress = { },
            id = 0,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Composable
private fun HomeScreenPreview() {
    GardenBookTheme {
        HomeScreenContent(
            onPlantCardPress = { },
            onAddPlantPress = { },
            plants = plantList,
        )
    }
}

@Preview(name = "AddPlantFABPreview")
@Composable
private fun AddPlantFABPreview() {
    GardenBookTheme {
        AddPlantFAB(
            onPress = { }
        )
    }

}