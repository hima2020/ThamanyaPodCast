package com.elgohry.feature_home.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import com.elgohry.core.components.HomeEmpty
import com.elgohry.core.components.HomeError
import com.elgohry.core.components.HomeList
import com.elgohry.core.components.HomeLoading
import com.elgohry.feature_home.presentation.state.UiState
import com.elgohry.feature_home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val listState = rememberLazyListState()

   // val pullState = rememberPullToRefreshState()


//    if (pullState.isRefreshing) {
//        LaunchedEffect(Unit) {
//            viewModel.refresh()
//            pullState.endRefresh()
//        }
//    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            AnimatedContent(
                targetState = uiState,
                label = "home-state"
            ) { state ->
                when (state) {
                    is UiState.Loading -> HomeLoading()

                    is UiState.Error -> HomeError(
                        title = "Something went wrong",
                        message = state.message,
                        onRetry = { viewModel.refresh() }
                    )

                    is UiState.Data -> {
                        if (state.sections.isEmpty()) {
                            HomeEmpty(onRefresh = { viewModel.refresh() })
                        } else {
                            HomeList(
                                items = state.sections,
                                listState = listState
                            )
                        }
                    }
                }
            }

//            PullToRefreshContainer(
//                modifier = Modifier
//                    .align(Alignment.TopCenter),
//                state = pullState
//            )
        }
    }
}