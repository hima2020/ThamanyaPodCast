package com.elgohry.feature_search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elgohry.core.common.AppDispatchers
import com.elgohry.feature_search.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import com.elgohry.core.common.Result
import com.elgohry.core.domain.entity.Section
import com.elgohry.feature_search.presentation.state.SearchUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()
    val results: StateFlow<SearchUiState> =
        _query
            .map { it.trim() }
            .distinctUntilChanged()
            .debounce(200)
            .flatMapLatest { q ->
                if (q.isBlank()) {
                    flowOf<SearchUiState>(SearchUiState.Data(emptyList()))

                } else {
                    flow {
                        emit(SearchUiState.Loading)
                        val res = withContext(dispatchers.io) { searchUseCase(q) }
                        emit(
                            when (res) {
                                is Result.Success -> SearchUiState.Data(res.data)
                                is Result.Error   -> SearchUiState.Error(res.message)
                            }
                        )
                    }
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchUiState.Loading
            )

    fun onQueryChange(q: String) {
        _query.value = q
    }
}