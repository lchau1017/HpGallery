package com.hpgallery.data

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hpgallery.feature.list.HpCharacterRow
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData
import com.hpgallery.ui.theme.HpGalleryTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HpCharacterRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun hpCharacterRow_DisplaysCorrectInformation() {
        val viewData = HpCharacterRowViewData(
            id = "1",
            name = "Harry Potter",
            actor = "Daniel Radcliffe",
            species = "Human",
            house = "Gryffindor"
        )

        // Set a flag to check if the onClick callback is triggered
        var clicked = false

        composeTestRule.setContent {
            HpGalleryTheme {
                HpCharacterRow(
                    modifier = Modifier.fillMaxSize(),
                    viewData = viewData,
                    onClick = { clicked = true }
                )
            }
        }

        composeTestRule.onNodeWithText("Harry Potter").assertIsDisplayed()
        composeTestRule.onNodeWithText("Actor: Daniel Radcliffe").assertIsDisplayed()
        composeTestRule.onNodeWithText("Species: Human").assertIsDisplayed()

        composeTestRule.onNodeWithText("Harry Potter").performClick()

        assert(clicked)
    }
}