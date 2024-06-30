package com.mnbpdx.gardenbook.ui.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.mnbpdx.gardenbook.model.plantList
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3Api
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var clickedPlant: String? = null

    @Before
    fun setUp() {
        clickedPlant = null
        composeTestRule.setContent {
            GardenBookTheme {
                HomeScreenContent(
                    onPlantCardPress = { plantName -> clickedPlant = plantName }
                )
            }
        }
    }

    @Test
    fun homeScreen_displaysAppBar() {
        // Verify that the app bar is displayed
        composeTestRule.onNodeWithText("Garden Book").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysPlantList() {
        // Verify that the first three plants are displayed
        plantList.take(3).forEach { plant ->
            composeTestRule.onNodeWithText(plant.name).assertIsDisplayed()
        }
    }

    @Test
    fun homeScreen_plantCardClick_triggersCallback() {
        // Click on the first plant card
        composeTestRule.onNodeWithText(plantList.first().name).performClick()

        // Verify that the callback was triggered with the correct plant name
        assert(clickedPlant == plantList.first().name)
    }

    @Test
    fun homeScreen_scrolls() {
        // Scroll the list to the end
        composeTestRule.onNode(hasScrollAction()).performScrollToIndex(plantList.size - 1)

        // Verify that the last item is displayed
        composeTestRule.onNodeWithText(plantList.last().name).assertIsDisplayed()
    }

    @Test
    fun homeScreen_bottomAppBarDisplayed() {
        // Verify that the bottom app bar is displayed
        composeTestRule.onNode(hasContentDescription("check mark icon")).assertIsDisplayed()
    }
}