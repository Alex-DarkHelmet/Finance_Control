package com.alex_bystrov.safemoney.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.alex_bystrov.safemoney.screens.home.model.HomeScreenViewState
import com.alex_bystrov.safemoney.screens.home.view.HomeScreenDisplay

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val viewState = viewModel.viewState.collectAsState(initial = HomeScreenViewState.NoItems)

    when(viewState.value) {
        is HomeScreenViewState.Display -> HomeScreenDisplay(viewState = viewState.value as HomeScreenViewState.Display)
        HomeScreenViewState.NoItems -> TODO()
    }
}