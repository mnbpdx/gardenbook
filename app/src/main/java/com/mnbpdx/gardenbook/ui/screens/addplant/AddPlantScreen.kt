package com.mnbpdx.gardenbook.ui.screens.addplant

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mnbpdx.gardenbook.ui.Destination
import com.mnbpdx.gardenbook.ui.GardenBookTopAppBar
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterial3Api
@Composable
internal fun AddPlantScreen(
    onBackPress: () -> Unit,
    viewModel: AddPlantViewModel = viewModel(),
) {
    BackHandler {
        onBackPress()
    }

    AddPlantScreenContent(
        onArrowBackPress = onBackPress,
        onSubmitButtonPress = viewModel::addPlant,
    )
}

@ExperimentalMaterial3Api
@Composable
internal fun AddPlantScreenContent(
    onArrowBackPress: () -> Unit,
    onSubmitButtonPress: (String, String) -> Unit,
) {
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            GardenBookTopAppBar(
                destination = Destination.AddPlantScreen,
                onArrowBackPress = onArrowBackPress,
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                TextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    textStyle = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    textStyle = MaterialTheme.typography.headlineLarge,
                )
                Button(
                    onClick = { onSubmitButtonPress(name.value, description.value) }
                ) {
                    Text(text = "Submit")
                }
            }
        }
    }
}