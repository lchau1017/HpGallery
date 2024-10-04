package com.hpgallery.core.event

sealed interface UiEvent {
    data object BackPress : UiEvent
    data class NavigateToCharacterDetail(val characterId: String) : UiEvent
}
