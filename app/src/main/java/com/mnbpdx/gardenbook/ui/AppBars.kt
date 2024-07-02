package com.mnbpdx.gardenbook.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.mnbpdx.gardenbook.R

@Composable
internal fun GardenBookBottomAppBar(
    isSelected: Boolean
) {
    NavigationBar {
        NavigationBarItem(
            selected = isSelected,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = stringResource(R.string.check_mark_icon_content_description)
                )
            }
        )
        NavigationBarItem(
            selected = isSelected,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(R.string.star_icon_content_description)
                )
            }
        )
        NavigationBarItem(
            selected = isSelected,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(R.string.search_icon_content_description)
                )
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
internal fun GardenBookTopAppBar(
    destination: Destination,
    onArrowBackPress: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        navigationIcon = {
            when (destination) {
                Destination.HomeScreen -> {}

                is Destination.DetailScreen -> {
                    IconButton(onClick = onArrowBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.arrow_back_icon_content_description)
                        )
                    }
                }

                is Destination.LoadingScreen -> {}
            }
        }
    )
}