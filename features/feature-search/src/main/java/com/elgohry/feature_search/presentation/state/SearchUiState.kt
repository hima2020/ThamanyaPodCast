package com.elgohry.feature_search.presentation.state

import com.elgohry.core.domain.entity.Section

sealed interface SearchUiState {
        data object Loading : SearchUiState
        data class Data(val sections: List<Section>) : SearchUiState
        data class Error(val message: String) : SearchUiState
    }