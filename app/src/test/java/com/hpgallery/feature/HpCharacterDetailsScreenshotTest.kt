package com.hpgallery.feature

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.hpgallery.CoroutineTestRule
import com.hpgallery.feature.details.HpCharacterDetailsScreenEmptyPreview
import com.hpgallery.feature.details.HpCharacterDetailsScreenErrorPreview
import com.hpgallery.feature.details.HpCharacterDetailsScreenSuccessPreview
import org.junit.Rule
import org.junit.Test

class HpCharacterDetailsScreenshotTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun testHpCharacterDetailsScreenSuccess() {
        paparazzi.snapshot {
            HpCharacterDetailsScreenSuccessPreview()
        }
    }


    @Test
    fun testHpCharacterDetailsScreenError() {
        paparazzi.snapshot {
            HpCharacterDetailsScreenErrorPreview()
        }
    }


    @Test
    fun testHpCharacterDetailsScreenEmpty() {
        paparazzi.snapshot {
            HpCharacterDetailsScreenEmptyPreview()
        }
    }
}
