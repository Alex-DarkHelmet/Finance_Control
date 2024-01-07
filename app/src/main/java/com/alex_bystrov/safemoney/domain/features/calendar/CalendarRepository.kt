package com.alex_bystrov.safemoney.domain.features.calendar

interface CalendarRepository {
    fun getStartWeekdayOfMonth(currentDate: String): Int

    fun getMonthName(currentDate: String): String

    fun getTotalDaysInMonth(currentDate: String): Int

    fun getNextMonth(currentMonth: Int): Int

    fun getLastMonth(currentMonth: Int): Int

    fun getFirstAndLastDayInMonth(date: String, isFirst: Boolean): String

    fun isFirstDayOfMonth(date: String): Boolean
}