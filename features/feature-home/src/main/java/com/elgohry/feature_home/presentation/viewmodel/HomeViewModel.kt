package com.elgohry.feature_home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elgohry.core.common.AppDispatchers
import com.elgohry.feature_home.domain.usecase.GetHomeSectionsUseCase
import com.elgohry.feature_home.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.elgohry.core.common.Result
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSections: GetHomeSectionsUseCase,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state = _state.asStateFlow()

    init { refresh() }

    fun refresh(page: Int? = null) {
        viewModelScope.launch(dispatchers.io) {
            when (val res = getHomeSections(page)) {
                is Result.Success -> _state.value = UiState.Data(res.data)
                is Result.Error -> _state.value = UiState.Error(res.message)
            }
        }
    }


}