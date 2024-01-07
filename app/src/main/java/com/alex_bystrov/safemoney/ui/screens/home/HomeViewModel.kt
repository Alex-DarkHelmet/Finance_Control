package com.alex_bystrov.safemoney.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex_bystrov.safemoney.common.OnEvent
import com.alex_bystrov.safemoney.domain.features.balance.BalanceRepository
import com.alex_bystrov.safemoney.domain.features.transactions.UserTransactionsRepository
import com.alex_bystrov.safemoney.ui.screens.home.model.HomeScreenEvent
import com.alex_bystrov.safemoney.ui.screens.home.model.HomeScreenViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(
    private val balanceRepository: BalanceRepository,
    private val transactionRepository: UserTransactionsRepository
) : ViewModel(), OnEvent<HomeScreenEvent> {

    private val currentDate = LocalDate.now().toString()

    private var _viewState = MutableStateFlow<HomeScreenViewState>(HomeScreenViewState.NoItems)
    val viewState: StateFlow<HomeScreenViewState> = _viewState.asStateFlow()


    override fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.DetailTransaction -> getCertainTransaction(event.id)
            HomeScreenEvent.EnterScreen -> fetchData(currentDate)
        }
    }

    private fun fetchData(currentDate: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentBalance = balanceRepository.getMonthlyBalance(currentDate)
            val totalBalance = balanceRepository.getTotalBalance()
            val userTransactions = transactionRepository.getMonthlyTransactions(date = currentDate)

            combine(currentBalance, totalBalance, userTransactions) { balance, total, transactions ->
                _viewState.emit(
                    HomeScreenViewState.Display(currentDate).copy(
                        currentMonth = currentDate,
                        expenseByMonth = balance.expenseSum,
                        incomeByMonth = balance.incomeSum,
                        balanceByMonth = balance.currentBalance,
                        totalBalance = total.totalAmount,
                        dailyTransactions = transactions
                    )
                )
            }
        }
    }

    private fun getCertainTransaction(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.getChosenTransaction(id)
        }
    }
}
