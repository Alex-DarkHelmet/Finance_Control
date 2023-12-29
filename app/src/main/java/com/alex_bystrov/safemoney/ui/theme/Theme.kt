package com.alex_bystrov.safemoney.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SafeMoneyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when(darkTheme) {
        true -> baseDarkPalette
        else -> baseDarkPalette
    }

    val typography = SafeMoneyTypography(
        balanceSize = TextStyle(
            fontSize = 25.sp,
            fontWeight = FontWeight(800)
        ),
        title = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(400)
        ),
        body = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight(400)
        ),
        headLine = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight(500)
        )
    )

    val shapes = SafeMoneyPadding(
        big = 20.dp,
        medium = 10.dp,
        small = 5.dp
    )

    CompositionLocalProvider (
        LocalSafeMoneyColors provides colors,
        LocalSafeMoneyTypography provides typography,
        LocalSafeMoneyShape provides shapes,
        content = content
    )
}