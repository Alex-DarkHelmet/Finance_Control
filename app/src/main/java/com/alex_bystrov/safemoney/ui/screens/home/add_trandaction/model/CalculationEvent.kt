package com.alex_bystrov.safemoney.ui.screens.home.add_trandaction.model

sealed class CalculationEvent {
    object Clear : CalculationEvent()
    object Decimal : CalculationEvent()
    data class Number(val number: Int) : CalculationEvent()
    data class Operations(val operators: CalculatorOperation): CalculationEvent()
}
