package com.alex_bystrov.safemoney.screens.home.view
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyAppTheme
import com.alex_bystrov.safemoney.ui.theme.SafeMoneyTheme

@Composable
fun TotalBalanceView(
    modifier: Modifier = Modifier,
    totalBalance: Double,
) {
    Card(
        modifier = modifier
            .padding(
                start = SafeMoneyAppTheme.padding.medium,
                end = SafeMoneyAppTheme.padding.medium,
                top = SafeMoneyAppTheme.padding.small,
                bottom = SafeMoneyAppTheme.padding.big
            ),
        shape = ShapeDefaults.Large,
        colors = CardDefaults.cardColors(SafeMoneyAppTheme.colors.containerColor),
        elevation = CardDefaults.cardElevation(20.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clickable {

                }
                .padding(vertical = SafeMoneyAppTheme.padding.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.total_balance_title),
                color = Color.LightGray,
                style = SafeMoneyAppTheme.typography.title
            )

            Text(
                text = totalBalance.toString(),
                color = Color.White,
                style = SafeMoneyAppTheme.typography.balanceSize
            )
        }
    }
}

@Composable
private fun TotalBalanceViewWithTheme() {
    SafeMoneyTheme {
        Surface {
            TotalBalanceView(totalBalance =  2003.98)
        }
    }
}

@Preview
@Composable
fun TotalBalancePreview() {
    TotalBalanceViewWithTheme()
}