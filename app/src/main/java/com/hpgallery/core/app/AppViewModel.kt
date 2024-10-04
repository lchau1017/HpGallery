package com.hpgallery.core.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpgallery.core.event.UiEvent
import com.hpgallery.domain.usecase.GetThemePreferenceUseCase
import com.hpgallery.domain.usecase.SaveThemePreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class AppViewModel @Inject constructor(
    getThemePreferenceUseCase: GetThemePreferenceUseCase,
    private val saveThemePreferenceUseCase: SaveThemePreferenceUseCase
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>(replay = 0)
    val uiEvent = _uiEvent.asSharedFlow()

    private val _isDarkTheme = getThemePreferenceUseCase().stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    fun onBackPressed() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.BackPress)
        }
    }

    fun onCharacterClicked(characterId: String) {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.NavigateToCharacterDetail(characterId))
        }
    }

    fun toggleTheme() {
        viewModelScope.launch {
            val newTheme = !_isDarkTheme.value
            saveThemePreferenceUseCase(newTheme)
        }
    }
}
