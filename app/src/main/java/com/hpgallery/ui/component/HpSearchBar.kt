package com.hpgallery.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HpSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChanged(it)
        },
        label = { Text("Search Characters") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}