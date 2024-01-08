package com.alex_bystrov.safemoney.domain.features.calendar.model

data class CalendarModel(
    val currentDay: Int,
    val month: String,
    val days: Int,
    val weekDays: List<WeekDays>,
    val startWeekday: Int
)

enum class WeekDays {
    Mon, Tue, Wed, Thu, Fri, Sat, Sun
}