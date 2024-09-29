package com.hpgallery.data

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hpgallery.feature.details.HpCharacterDetailsCard
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData
import com.hpgallery.ui.theme.HpGalleryTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HpCharacterDetailsCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun hpCharacterDetailsCard_DisplaysCorrectInformation() {
        // Sample test data
        val viewData = HpCharacterDetailsCardViewData(
            id = "1",
            name = "Harry Potter",
            actor = "Daniel Radcliffe",
            species = "Human",
            dateOfBirth = "31 July 1980",
            house = "Gryffindor",
            status = "Alive",
            image = "https://example.com/harry_potter.jpg"
        )

        // Set up the composable under test
        composeTestRule.setContent {
            HpGalleryTheme {
                HpCharacterDetailsCard(
                    viewData = viewData,
                    modifier = Modifier
                )
            }
        }

        // Assert that the character's name, actor, species, DOB, and status are displayed
        composeTestRule.onNodeWithText("Harry Potter").assertIsDisplayed()
        composeTestRule.onNodeWithText("Actor: Daniel Radcliffe").assertIsDisplayed()
        composeTestRule.onNodeWithText("Species: Human").assertIsDisplayed()
        composeTestRule.onNodeWithText("DOB: 31 July 1980").assertIsDisplayed()
        composeTestRule.onNodeWithText("Status: Alive").assertIsDisplayed()

        // Assert that the image is displayed (by content description)
        composeTestRule.onNodeWithContentDescription("Harry Potter").assertIsDisplayed()
    }

    @Test
    fun hpCharacterDetailsCard_HidesImage_WhenUrlIsNullOrEmpty() {
        // Sample test data without an image
        val viewData = HpCharacterDetailsCardViewData(
            id = "1",
            name = "Hermione Granger",
            actor = "Emma Watson",
            species = "Human",
            dateOfBirth = "19 September 1979",
            house = "Gryffindor",
            status = "Alive",
            image = null // No image provided
        )

        // Set up the composable under test
        composeTestRule.setContent {
            HpGalleryTheme {
                HpCharacterDetailsCard(
                    viewData = viewData,
                    modifier = Modifier
                )
            }
        }

        // Assert that the character's name and other details are displayed
        composeTestRule.onNodeWithText("Hermione Granger").assertIsDisplayed()
        composeTestRule.onNodeWithText("Actor: Emma Watson").assertIsDisplayed()
        composeTestRule.onNodeWithText("Species: Human").assertIsDisplayed()
        composeTestRule.onNodeWithText("DOB: 19 September 1979").assertIsDisplayed()
        composeTestRule.onNodeWithText("Status: Alive").assertIsDisplayed()

        // Assert that there is no image node with the content description "Hermione Granger"
        composeTestRule.onNodeWithContentDescription("Hermione Granger").assertDoesNotExist()
    }
}