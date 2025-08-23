package com.elgohry.feature_home.presentation.screens
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.elgohry.core.components.AppToolBar
import com.elgohry.core.components.HomeEmpty
import com.elgohry.core.components.HomeError
import com.elgohry.core.components.HomeList
import com.elgohry.core.components.HomeLoading
import com.elgohry.feature_home.presentation.state.Phase
import com.elgohry.feature_home.presentation.state.UiState
import com.elgohry.feature_home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()
    val appending by viewModel.appending.collectAsState()


Column {

    AppToolBar(onSearchClick =onSearchClick)
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            LaunchedEffect(listState) {
                snapshotFlow {
                    val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: -1
                    val total = listState.layoutInfo.totalItemsCount
                    lastVisible to total
                }
                    .distinctUntilChanged()
                    .collect { (last, total) ->
                        val threshold = 3
                        if (total > 0 && last >= total - threshold) {
                            viewModel.loadMore()
                        }
                    }
            }
            val phase = when (uiState) {
                is UiState.Loading -> Phase.Loading
                is UiState.Error   -> Phase.Error
                is UiState.Data    -> Phase.Content
                else -> {}
            }
            AnimatedContent(targetState = phase, label = "home-phase") { p ->
                when (p) {
                    Phase.Loading -> HomeLoading()

                    Phase.Error -> {
                        val err = uiState as UiState.Error
                        HomeError(
                            title = "Something went wrong",
                            message = err.message,
                            onRetry = { viewModel.refresh() }
                        )
                    }

                    Phase.Content -> {
                        val data = uiState as UiState.Data
                        if (data.sections.isEmpty()) {
                            HomeEmpty(onRefresh = { viewModel.refresh() })
                        } else {
                            HomeList(
                                items = data.sections,
                                listState = listState,
                                showFooterLoading = appending
                            )
                        }
                    }
                }
            }
        }
    }
    }
        }
