package com.mnbpdx.gardenbook.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NavigationViewModelTest {

    private val viewModel = NavigationViewModel()

    @Test
    fun `WHEN navigateToHomeScreen is called THEN destination set to home screen`() {
        viewModel.navigateToHomeScreen()
        assertThat(viewModel.destination.value).isEqualTo(Destination.HomeScreen)
    }

    @Test
    fun `WHEN navigateToDetailScreen is called THEN destination set to detail screen`() {
        viewModel.navigateToDetailScreen("test plant name")
        assertThat(viewModel.destination.value).isEqualTo(Destination.DetailScreen("test plant name"))
    }
}