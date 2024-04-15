package com.example.ticketmasterapp.util

import java.text.SimpleDateFormat
import java.util.Locale

fun convertDateFormat(inputDate: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

    val date = inputFormat.parse(inputDate)

    return date?.let { outputFormat.format(it) }
}