package com.hpgallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpgallery.domain.usecase.GetThemePreferenceUseCase
import com.hpgallery.domain.usecase.SaveThemePreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class AppViewModel @Inject constructor(
    getThemePreferenceUseCase: GetThemePreferenceUseCase,
    private val saveThemePreferenceUseCase: SaveThemePreferenceUseCase
) : ViewModel() {

    private val _isDarkTheme = getThemePreferenceUseCase()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    // Toggle and save the theme preference using the use case
    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = !_isDarkTheme.value
            saveThemePreferenceUseCase(newTheme)
        }
    }
}