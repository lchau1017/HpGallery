package com.hpgallery.feature.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hpgallery.domain.usecase.GetHpCharacterDetailsUseCase
import com.hpgallery.feature.details.mapper.toHpCharacterDetailsCardViewData
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsErrorViewData
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HpCharacterDetailsViewModel @Inject constructor(
    private val getHpCharacterDetailsUseCase: GetHpCharacterDetailsUseCase
) : ViewModel() {
    private val _hpCharacterDetailsState =
        MutableStateFlow<HpCharacterDetailsViewData>(HpCharacterDetailsViewData.Empty)
    val hpCharacterDetailsState: StateFlow<HpCharacterDetailsViewData> = _hpCharacterDetailsState

    fun loadCharacterDetails(id: String) {
        viewModelScope.launch {
            getHpCharacterDetailsUseCase(id).map { character ->
                character?.let { HpCharacterDetailsViewData.Success(hpCharacterDetailsCardViewData = it.toHpCharacterDetailsCardViewData()) }
                    ?: HpCharacterDetailsViewData.Empty
            }.catch { throwable ->
                _hpCharacterDetailsState.value = HpCharacterDetailsViewData.Error(
                    hpCharacterDetailsErrorViewData = HpCharacterDetailsErrorViewData(
                        errorMessage = throwable.message ?: "Could’t load characters"
                    )
                )
            }.collect {
                _hpCharacterDetailsState.value = it
            }
        }
    }
}