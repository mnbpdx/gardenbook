package com.mnbpdx.gardenbook

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.mnbpdx.gardenbook.ui.detail.DetailScreenContent
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var backPressed = false
    private val testPlantName = "Test Plant"

    @Before
    fun setUp() {
        backPressed = false
        composeTestRule.setContent {
            GardenBookTheme {
                DetailScreenContent(
                    plantName = testPlantName,
                    onArrowBackPress = { backPressed = true }
                )
            }
        }
    }

    @Test
    fun detailScreen_displaysPlantName() {
        composeTestRule.onNodeWithText(testPlantName).assertIsDisplayed()
    }

    @Test
    fun detailScreen_displaysImageCarousel() {
        composeTestRule.onNodeWithContentDescription("Carousel image 0").assertIsDisplayed()
    }

    @Test
    fun detailScreen_displaysThreeImagesInCarousel() {
        repeat(3) { index ->
            composeTestRule.onNodeWithContentDescription("Carousel image $index")
        }
    }

    @Test
    fun detailScreen_backPress_triggersCallback() {
        composeTestRule.onNodeWithContentDescription("up icon").performClick()
        assert(backPressed)
    }
}
