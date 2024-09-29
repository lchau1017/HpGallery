package com.hpgallery.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpgallery.domain.usecase.GetHpCharactersUseCase
import com.hpgallery.feature.list.mapper.toHpCharacterRowViewData
import com.hpgallery.feature.list.viewdata.HpCharacterListErrorViewData
import com.hpgallery.feature.list.viewdata.HpCharacterListViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HpCharacterListViewModel @Inject constructor(
    private val getHpCharactersUseCase: GetHpCharactersUseCase
) : ViewModel() {
    private val _hpCharacterListState =
        MutableStateFlow<HpCharacterListViewData>(HpCharacterListViewData.Loading)
    val hpCharacterListState: StateFlow<HpCharacterListViewData> = _hpCharacterListState

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            getHpCharactersUseCase().map { characters ->
                HpCharacterListViewData.Success(hpCharacterRowViewData = characters.map { it.toHpCharacterRowViewData() })
            }.catch { throwable ->
                _hpCharacterListState.value = HpCharacterListViewData.Error(
                    hpCharacterListErrorViewData = HpCharacterListErrorViewData(
                        errorMessage = throwable.message ?: "Could’t load characters"
                    )
                )
            }.collect {
                _hpCharacterListState.value = it
            }
        }
    }
}