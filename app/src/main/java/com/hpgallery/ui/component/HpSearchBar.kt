package com.hpgallery.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallery.ui.utils.DualModePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HpSearchBar(
    query: String, onQueryChange: (String) -> Unit
) {
    CenterAlignedTopAppBar(modifier = Modifier.padding(vertical = 8.dp),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
        ),
        title = {
            OutlinedTextField(
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text(text = "Search...") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp), // Rounded corners for the search bar
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Filled.Clear, contentDescription = "Clear Text"
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedLeadingIconColor = LocalColourScheme.current.textSecondary,
                    unfocusedLeadingIconColor = LocalColourScheme.current.textSecondary,
                    disabledLeadingIconColor = LocalColourScheme.current.textSecondary,
                    focusedTextColor = LocalColourScheme.current.textPrimary,
                    unfocusedTextColor = LocalColourScheme.current.textPrimary,
                    disabledTextColor = LocalColourScheme.current.textPrimary,
                    focusedContainerColor = LocalColourScheme.current.backgroundSecondary,
                    unfocusedContainerColor = LocalColourScheme.current.backgroundSecondary,
                    disabledContainerColor = LocalColourScheme.current.backgroundSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedPlaceholderColor = LocalColourScheme.current.textSecondary,
                    unfocusedPlaceholderColor = LocalColourScheme.current.textSecondary,
                    disabledPlaceholderColor = LocalColourScheme.current.textSecondary,
                    cursorColor = LocalColourScheme.current.textPrimary,
                    focusedTrailingIconColor = LocalColourScheme.current.textSecondary,
                    unfocusedTrailingIconColor = LocalColourScheme.current.textSecondary,
                    disabledTrailingIconColor = LocalColourScheme.current.textSecondary,
                ),
            )
        })
}

@DualModePreview
@Composable
fun HpSearchBarPreview() {
    HpGalleryTheme {
        HpSearchBar(query = "Harry Potter", onQueryChange = { /* Do nothing */ })
    }
}