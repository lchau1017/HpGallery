package com.hpgallery.feature

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.hpgallery.CoroutineTestRule
import com.hpgallery.feature.list.HpCharacterRowPreview
import org.junit.Rule
import org.junit.Test

class HpCharacterRowScreenshotTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun hpCharacterRow() {
        paparazzi.snapshot {
            HpCharacterRowPreview()
        }
    }
}
