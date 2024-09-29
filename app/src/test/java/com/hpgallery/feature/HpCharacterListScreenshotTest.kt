package com.hpgallery.feature

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.hpgallery.CoroutineTestRule
import com.hpgallery.feature.list.HpCharacterListScreenEmptyPreview
import com.hpgallery.feature.list.HpCharacterListScreenErrorPreview
import com.hpgallery.feature.list.HpCharacterListScreenSuccessPreview
import org.junit.Rule
import org.junit.Test

class HpCharacterListScreenshotTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    val paparazzi = Paparazzi(deviceConfig = DeviceConfig.PIXEL_5)

    @Test
    fun testHpCharacterListScreenSuccess() {
        paparazzi.snapshot {
            HpCharacterListScreenSuccessPreview()
        }
    }

    @Test
    fun testHpCharacterListScreenError() {
        paparazzi.snapshot {
            HpCharacterListScreenErrorPreview()
        }
    }

    @Test
    fun testHpCharacterListScreenEmpty() {
        paparazzi.snapshot {
            HpCharacterListScreenEmptyPreview()
        }
    }
}
