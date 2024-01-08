package com.alex_bystrov.safemoney.screens.home.add_trandaction.model

sealed class CalculatorOperation(val symbol: String) {
    object Add : CalculatorOperation(symbol = "+")
    object Subtract : CalculatorOperation(symbol = "-")
    object Multiply : CalculatorOperation(symbol = "*")
    object Divide : CalculatorOperation(symbol = "/")
}
