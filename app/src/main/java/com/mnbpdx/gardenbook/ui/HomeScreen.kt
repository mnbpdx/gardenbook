package com.mnbpdx.gardenbook.ui

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mnbpdx.gardenbook.R
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme

@ExperimentalMaterial3Api
@Composable
internal fun HomeScreen() {
    Scaffold(
        topBar = { GardenBookTopAppBar() },
        bottomBar = { GardenBookBottomAppBar(isSelected = true) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            color = Color.Gray,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
//                Spacer(modifier = Modifier.height(16.dp))
//                BasicPlantCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))
                
                val plants : List<String> = listOf(
                    "pothos",
                    "daisy",
                    "tomato",
                    "rosemary",
                    "greg",
                )

                plants.map { plant ->
                Spacer(modifier = Modifier.height(16.dp))
                    BasicPlantCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))
                }


            }
        }
    }
}

@Composable
private fun BasicPlantCard(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Surface {
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
                        text = "plant name",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge
                    )

                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun BasicPlantCardPreview() {
//    BasicPlantCard()
//}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HomeScreenPreview() {
    GardenBookTheme {
        HomeScreen()
    }
}