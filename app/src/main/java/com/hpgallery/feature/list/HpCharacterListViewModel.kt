package com.hpgallery.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpgallery.domain.usecase.SearchHpCharactersUseCase
import com.hpgallery.feature.list.mapper.toHpCharacterRowViewData
import com.hpgallery.feature.list.viewdata.HpCharacterListErrorViewData
import com.hpgallery.feature.list.viewdata.HpCharacterListViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class HpCharacterListViewModel @Inject constructor(
    private val searchHpCharactersUseCase: SearchHpCharactersUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _hpCharacterListState =
        MutableStateFlow<HpCharacterListViewData>(HpCharacterListViewData.Loading)
    val hpCharacterListState: StateFlow<HpCharacterListViewData> = _hpCharacterListState

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            _searchQuery.debounce(300).distinctUntilChanged().flatMapLatest { query ->
                searchHpCharactersUseCase(query).map { characters ->
                    if (characters.isEmpty()) {
                        HpCharacterListViewData.Empty
                    } else {
                        HpCharacterListViewData.Success(
                            hpCharacterRowViewData = characters.map { it.toHpCharacterRowViewData() }
                        )
                    }
                }.catch { throwable ->
                    _hpCharacterListState.value = HpCharacterListViewData.Error(
                        hpCharacterListErrorViewData = HpCharacterListErrorViewData(
                            errorMessage = throwable.message ?: "Could’t load characters"
                        )
                    )
                }
            }.collect {
                _hpCharacterListState.value = it
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}
