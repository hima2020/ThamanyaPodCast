package com.elgohry.feature_home.presentation.state

import com.elgohry.core.domain.entity.Section

sealed interface UiState {
        data object Loading : UiState
        data class Data(val sections: List<Section>) : UiState
        data class Error(val message: String) : UiState
    }