package com.alex_bystrov.safemoney.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex_bystrov.safemoney.common.ObtainEvent
import com.alex_bystrov.safemoney.domain.features.balance.repository.BalanceRepository
import com.alex_bystrov.safemoney.domain.features.transactions.repository.UserTransactionsRepository
import com.alex_bystrov.safemoney.ui.screens.home.model.HomeScreenEvent
import com.alex_bystrov.safemoney.ui.screens.home.model.HomeScreenViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: UserTransactionsRepository
) : ViewModel(), ObtainEvent<HomeScreenEvent> {

    private var currentDate: LocalDate = LocalDate.now()

    private var _viewState = MutableStateFlow<HomeScreenViewState>(HomeScreenViewState.Display())
    val viewState: StateFlow<HomeScreenViewState> = _viewState.asStateFlow()


    override fun obtainEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.DetailStatisticSavingByMonth -> TODO()
            is HomeScreenEvent.DetailTransaction -> TODO()
            HomeScreenEvent.EnterScreen -> fetchBalanceData(currentDate)
        }
    }

    private fun fetchBalanceData(currentDate: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            TODO()
        }
    }
}
