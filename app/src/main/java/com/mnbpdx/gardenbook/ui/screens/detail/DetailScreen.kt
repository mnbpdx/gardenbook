package com.mnbpdx.gardenbook.ui.screens.detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mnbpdx.gardenbook.R
import com.mnbpdx.gardenbook.ui.Destination
import com.mnbpdx.gardenbook.ui.GardenBookTopAppBar

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
        plantName = plantName,
        onArrowBackPress = onBackPress,
    )
}

@ExperimentalMaterial3Api
@Composable
internal fun DetailScreenContent(
    plantName: String,
    onArrowBackPress: () -> Unit,
) {
    Scaffold(
        topBar = {
            GardenBookTopAppBar(
                destination = Destination.DetailScreen(plantName),
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
                Text(
                    text = plantName,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineLarge,
                )

                Spacer(modifier = Modifier.height(16.dp))

                val images = listOf(
                    R.drawable.leaf,
                    R.drawable.leaf,
                    R.drawable.westerland_climbing_rose,
                )
                ImageCarousel(images = images)
                Spacer(modifier = Modifier.height(16.dp))
                PlantInfo(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    titleText = "Title",
                    bodyText = "These changes introduce a more detailed Plant model and update the DetailScreen to use this model. Here's a summary of the changes:\n" + "\n" + "Created a Plant data class with various properties like id, name, scientific name, description, care level, water frequency, and an image resource id.\n" + "Added an enum CareLevel to represent the difficulty of caring for a plant.\n" + "Created a SamplePlantData object with a list of sample plants and a list of plant names.\n" + "Updated the DetailScreen and DetailScreenContent to use the Plant model instead of just a plant name.\n" + "Enhanced the DetailScreenContent to display more information about the plant, including its scientific name, care level, watering frequency, and description.\n" + "Updated the Preview to use a sample plant from SamplePlantData.\n" + "\n" + "These changes provide a more comprehensive and data-rich detail screen for each plant. You can further customize the layout and styling to fit your app's design.\n" + "Remember to replace the placeholder image (R.drawable.leaf) with actual plant images when you have them available. You might want to consider having multiple images per plant in the future, which would make better use of the carousel feature.",
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(images: List<Int>) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    Box(modifier = Modifier
        .fillMaxWidth(fraction = .66f)
        .height(300.dp)
        .padding(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 32.dp,
        ) { page ->
            Surface(
                shape = RoundedCornerShape(48.dp)
            ) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "Carousel image $page",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Row(
            Modifier
                .height(40.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(images.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
fun PlantInfo(
    modifier: Modifier = Modifier,
    titleText: String,
    bodyText: String
) {
    Column(modifier = modifier) {
        Text(
            text = titleText,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = bodyText,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

//@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
//@Preview
//@Composable
//private fun DetailScreenPreview() {
//    GardenBookTheme {
//        DetailScreenContent(
//            plantName = "Plant Name",
//            onArrowBackPress = {},
//        )
//    }
//}