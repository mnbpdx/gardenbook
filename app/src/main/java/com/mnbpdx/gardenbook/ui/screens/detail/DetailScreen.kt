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
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mnbpdx.gardenbook.ui.screens.loading.LoadingScreen


@ExperimentalMaterial3Api
@Composable
internal fun DetailScreen(
    viewModel: DetailScreenViewModel = viewModel(),
    onBackPress: () -> Unit,
    id: Int,
) {
    BackHandler {
        onBackPress()
    }

    viewModel.loadPlant(id)

    when (val screenState = viewModel.screenState.collectAsState().value) {
        is DetailScreenViewModel.ScreenState.Loading -> {
            LoadingScreen()
        }

        is DetailScreenViewModel.ScreenState.Loaded -> {
            DetailScreenContent(
                screenState = screenState,
                onArrowBackPress = onBackPress,
            )
        }

        is DetailScreenViewModel.ScreenState.Error -> TODO("refresh screen here maybe?")
    }
}

@ExperimentalMaterial3Api
@Composable
internal fun DetailScreenContent(
    screenState: DetailScreenViewModel.ScreenState.Loaded,
    onArrowBackPress: () -> Unit,
) {
    Scaffold(
        topBar = {
            GardenBookTopAppBar(
                destination = Destination.DetailScreen(screenState.plant.id), // TODO: ready: shouldn't be using this destination in this way. also need to switch to compose nav anyway
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
                    text = screenState.plant.name,
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
                    bodyText = screenState.plant.description,
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(images: List<Int>) {
    val pagerState = rememberPagerState(pageCount = { images.size })
    Box(
        modifier = Modifier
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
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
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