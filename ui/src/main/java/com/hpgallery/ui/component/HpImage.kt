package com.hpgallery.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hpgallery.ui.DualModePreview
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallgery.ui.R

@Composable
fun HpImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.clip(CircleShape)
        )
    }
}

@DualModePreview
@Composable
fun HpImagePreview() {
    HpGalleryTheme {
        HpImage(
            modifier = Modifier
                .background(LocalColourScheme.current.backgroundPrimary),
            imageUrl = "https://uploads.guim.co.uk/2024/07/24/Tesco-finest-logo-light-mode-3x.png",
            contentDescription = null
        )
    }
}
