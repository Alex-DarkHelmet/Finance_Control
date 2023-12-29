package com.alex_bystrov.safemoney.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class SafeMoneyColors(
    val primaryText: Color,
    val secondaryText: Color,
    val primaryBackground: Color,
    val containerColor: Color,
    val incomeText: Color,
    val expenseText: Color,
    val actionColor: Color,
    val chosenDayBackground: Color,
    val tintChosenDay: Color,
    val currentDayColor:Color
)

data class SafeMoneyTypography(
    val balanceSize: TextStyle,
    val title: TextStyle,
    val headLine: TextStyle,
    val body: TextStyle,
 )

data class SafeMoneyPadding(
    val big: Dp,
    val medium: Dp,
    val small: Dp,
)

object SafeMoneyAppTheme {
    val colors: SafeMoneyColors
        @Composable
        get() = LocalSafeMoneyColors.current

    val typography: SafeMoneyTypography
        @Composable
        get() = LocalSafeMoneyTypography.current

    val padding: SafeMoneyPadding
        @Composable
        get() = LocalSafeMoneyShape.current
}

enum class CategoryColor {
    Blue, Grey, Red, Green
}

val LocalSafeMoneyColors = staticCompositionLocalOf<SafeMoneyColors> {
    error("No colors provided")
}

val LocalSafeMoneyTypography = staticCompositionLocalOf<SafeMoneyTypography> {
    error("No typographies provided")
}

val LocalSafeMoneyShape = staticCompositionLocalOf<SafeMoneyPadding> {
    error("No padding provided")
}