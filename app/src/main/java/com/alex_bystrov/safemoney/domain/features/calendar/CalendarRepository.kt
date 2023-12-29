package com.alex_bystrov.safemoney.domain.features.calendar

interface CalendarRepository {

    fun getStartWeekdayOfMonth(currentDate: String): String

    fun getMonthName(currentDate: String): String

    fun getTotalDaysInMonth(currentDate: String): Int

    fun getNextMonth(currentMonth: Int): Int

    fun getLastMonth(currentMonth: Int): Int

    fun getLimitsDatesInMonth(date: String, isStart: Boolean): String
}