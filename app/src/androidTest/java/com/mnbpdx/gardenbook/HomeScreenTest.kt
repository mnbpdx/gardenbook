package com.mnbpdx.gardenbook

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import com.mnbpdx.gardenbook.model.Location
import com.mnbpdx.gardenbook.model.Plant
import com.mnbpdx.gardenbook.model.plantList
import com.mnbpdx.gardenbook.ui.screens.home.HomeScreenContent
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterial3Api
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var clickedPlant: Int? = null
    private var onAddPlantPressed = false

    @Before
    fun setUp() {
        clickedPlant = null
        onAddPlantPressed = false
        composeTestRule.setContent {
            GardenBookTheme {
                HomeScreenContent(
                    onPlantCardPress = { id -> clickedPlant = id },
                    onAddPlantPress = { onAddPlantPressed = true },
                    plants = plantList
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
        assert(clickedPlant == plantList.first().id)
    }

    @Test
    fun homeScreen_scrolls_to_the_bottom_of_the_list() {
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

    // lol idk bout these tests, they seem kinda dumb. eventually we should be doing
    // more high level tests like navigation or user flows
    @Test
    fun homeScreen_fabPressed_triggersCallback() {
        // Click on the floating action button
        composeTestRule.onNode(hasContentDescription("add icon")).performClick()

        // Verify that the callback was triggered
        assert(onAddPlantPressed)
    }
}