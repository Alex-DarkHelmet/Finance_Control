package com.alex_bystrov.safemoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.alex_bystrov.safemoney.data.repository.impl.BalanceRepositoryImpl
import com.alex_bystrov.safemoney.domain.features.balance.BalanceRepository
import com.alex_bystrov.safemoney.domain.features.transactions.UserTransactionsRepository
import com.alex_bystrov.safemoney.ui.screens.home.HomeScreen
import com.alex_bystrov.safemoney.ui.screens.home.HomeViewModel
import com.alex_bystrov.safemoney.ui.screens.home.view.HomeScreenDisplay
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SafeMoneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    lateinit var balanceRepository: BalanceRepository
                    lateinit var transactionsRepository: UserTransactionsRepository
                    val viewModel = HomeViewModel(
                        balanceRepository = balanceRepository,
                        transactionRepository = transactionsRepository
                    )
                    HomeScreen(viewModel = viewModel)
                }
            }
        }
    }
}