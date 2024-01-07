package com.alex_bystrov.safemoney.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.alex_bystrov.safemoney.ui.screens.home.model.HomeScreenViewState
import com.alex_bystrov.safemoney.ui.screens.home.view.HomeScreenDisplay

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

    when(viewState.value) {
        is HomeScreenViewState.Display -> HomeScreenDisplay(viewState = viewState.value as HomeScreenViewState.Display)

        HomeScreenViewState.NoItems -> TODO()
    }
}