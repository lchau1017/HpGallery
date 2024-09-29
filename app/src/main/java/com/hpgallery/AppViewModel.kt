package com.hpgallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpgallery.domain.usecase.GetThemePreferenceUseCase
import com.hpgallery.domain.usecase.SaveThemePreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val getThemePreferenceUseCase: GetThemePreferenceUseCase,
    private val saveThemePreferenceUseCase: SaveThemePreferenceUseCase
) : ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    init {
        // Observe the theme preference using the use case
        getThemePreferenceUseCase().onEach { theme -> _isDarkTheme.value = theme }
            .launchIn(viewModelScope)
    }

    // Toggle and save the theme preference using the use case
    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = !_isDarkTheme.value
            _isDarkTheme.value = newTheme
            saveThemePreferenceUseCase(newTheme)
        }
    }
}