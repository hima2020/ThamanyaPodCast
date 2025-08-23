package com.elgohry.feature_search.presentation.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.elgohry.core.components.HomeEmpty
import com.elgohry.core.components.HomeError
import com.elgohry.core.components.HomeList
import com.elgohry.core.components.HomeLoading
import com.elgohry.feature_search.presentation.state.SearchUiState
import com.elgohry.feature_search.presentation.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier, viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.results.collectAsState()
    val query by viewModel.query.collectAsState()


    val snackbarHostState = remember { SnackbarHostState() }

    val focusManager = LocalFocusManager.current
    val listState = rememberLazyListState()

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            // Search field with debounce functionality
            OutlinedTextField(
                value = query,
                onValueChange = { query ->
                    viewModel.onQueryChange(query)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                label = { Text("Search") },
                placeholder = { Text("Enter search term...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = {
                            viewModel.onQueryChange("")
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear search"
                            )
                        }
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // Hide keyboard when search is pressed
                        focusManager.clearFocus()
                    }
                )
            )
        }
    ) { paddingValues ->
        // The main UI content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AnimatedContent(
                targetState = uiState,
                label = "home-state"
            ) { state ->
                when (state) {
                    is SearchUiState.Loading -> {}

                    is SearchUiState.Error -> HomeError(
                        title = "Something went wrong",
                        message = state.message,
                        onRetry = { viewModel.onQueryChange(query) }
                    )

                    is SearchUiState.Data -> {
                        if (state.sections.isEmpty()) {
                            HomeEmpty(onRefresh = { viewModel.onQueryChange(query) })
                        } else {
                            HomeList(
                                items = state.sections,
                                listState = listState
                            )
                        }
                    }
                }
            }
        }
    }
}
