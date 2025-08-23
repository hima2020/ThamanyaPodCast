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
import com.elgohry.core.domain.entity.Section
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeSections: GetHomeSectionsUseCase,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state = _state.asStateFlow()

    private val _appending = MutableStateFlow(false)
    val appending = _appending.asStateFlow()

    private var page = 1
    private var endReached = false
    private val items = mutableListOf<Section>()

    init { refresh() }

    fun refresh(page: Int? = null) {
        viewModelScope.launch(dispatchers.io) {
            _state.value = UiState.Loading
            val firstPage = page ?: 1
            this@HomeViewModel.page = firstPage
            endReached = false
            items.clear()

            when (val res = getHomeSections(firstPage)) {
                is Result.Success -> {
                    items += res.data
                    endReached = res.data.isEmpty()
                    _state.value = UiState.Data(items.toList())
                }
                is Result.Error -> _state.value = UiState.Error(res.message)
            }
        }
    }

    fun loadMore() {
        if (_appending.value || endReached) return
        _appending.value = true

        viewModelScope.launch(dispatchers.io) {
            val next = page + 1
            when (val res = getHomeSections(next)) {
                is Result.Success -> {
                    val newItems = res.data
                    if (newItems.isEmpty()) {
                        endReached = true
                    } else {
                        items += newItems
                        page = next
                        _state.value = UiState.Data(items.toList())
                    }
                }
                is Result.Error -> {
                }
            }
            _appending.value = false
        }
    }
}