package com.alex_bystrov.safemoney.screens.home.add_trandaction.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex_bystrov.safemoney.R
import com.alex_bystrov.safemoney.screens.home.add_trandaction.model.CalculationEvent
import com.alex_bystrov.safemoney.screens.home.add_trandaction.model.CalculatorOperation

@Composable
fun InputTransactionView(
    modifier: Modifier = Modifier,
    isClick: (CalculationEvent) -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        InputKeyboard(
            modifier = modifier,
            isClick = {
                isClick(it)
            }
        )
    }
}

@Composable
fun InputKeyboard(
    modifier: Modifier = Modifier,
    spacer: Dp = 12.dp,
    isClick: (CalculationEvent) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacer)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacer)
        ) {
            CalculateButton(
                modifier = modifier,
                symbol = "7",
                isClicked = {
                    isClick(CalculationEvent.Number(7))
                }
            )

            CalculateButton(
                symbol = "8",
                isClicked = {
                    isClick(CalculationEvent.Number(8))
                }
            )

            CalculateButton(
                symbol = "9",
                isClicked = {
                    isClick(CalculationEvent.Number(9))
                }
            )

            CalculateButton(
                symbol = "+",
                isDigit = false,
                isClicked = {
                    isClick(CalculationEvent.Operations(CalculatorOperation.Add))
                }
            )
        }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spacer)
        ) {
            CalculateButton(
                symbol = "3",
                isClicked = {
                    isClick(CalculationEvent.Number(7))
                }
            )

            CalculateButton(
                symbol = "4",
                isClicked = {
                    isClick(CalculationEvent.Number(8))
                }
            )

            CalculateButton(
                symbol = "5",
                isClicked = {
                    isClick(CalculationEvent.Number(9))
                }
            )

            CalculateButton(
                symbol = "-",
                isDigit = false,
                isClicked = {
                    isClick(CalculationEvent.Operations(CalculatorOperation.Subtract))
                }
            )
        }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spacer)
        ) {
            CalculateButton(
                symbol = "1",
                isClicked = {
                    isClick(CalculationEvent.Number(1))
                }
            )

            CalculateButton(
                symbol = "2",
                isClicked = {
                    isClick(CalculationEvent.Number(2))
                }
            )

            CalculateButton(
                symbol = "3",
                isClicked = {
                    isClick(CalculationEvent.Number(3))
                }
            )

            CalculateButton(
                symbol = "*",
                isDigit = false,
                isClicked = {
                    isClick(CalculationEvent.Operations(CalculatorOperation.Multiply))
                }
            )
        }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spacer)
        ) {
            CalculateButton(
                symbol = ".",
                isClicked = {
                    isClick(CalculationEvent.Decimal)
                }
            )

            CalculateButton(
                symbol = "0",
                isClicked = {
                    isClick(CalculationEvent.Number(0))
                }
            )

            CalculateButton(
                symbol = R.drawable.clear_char_arrow_24,
                isClicked = {
                    isClick(CalculationEvent.Clear)
                }
            )

            CalculateButton(
                symbol = "/",
                isDigit = false,
                isClicked = {
                    isClick(CalculationEvent.Operations(CalculatorOperation.Divide))
                }
            )
        }
    }
}

@Composable
private fun CalculateButton(
    modifier: Modifier = Modifier,
    symbol: Any,
    isDigit: Boolean = true,
    isClicked: () -> Unit
) {
    Box(
        modifier = modifier

            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = modifier
                .clip(CircleShape)
                .background(
                    if (isDigit || symbol.toString() == "."
                    ) {
                        Color.Black
                    } else {
                        Color.Gray
                    }
                )
                .padding(4.dp),
            onClick = {
                isClicked()
            }
        ) {
            if (symbol is String) {
                Text(
                    modifier = modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    text = symbol
                )
            } else {
                Icon(
                    modifier = modifier,
                    painter = painterResource(id = symbol as Int),
                    contentDescription = "test",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun InputPreview() {
    InputKeyboard(isClick = {})
}


@Preview
@Composable
fun AddTransactionPreview() {
    InputTransactionView(isClick = {})
}
