package utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun formatStringByDotPattern(dateInput: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale("ru"))
    return LocalDate.parse(dateInput.trim(), formatter)
}

fun formatDateByDotPattern(date: LocalDate): String {
    return "${String.format("%02d", date.dayOfMonth)}${String.format("%02d", date.monthValue)}${date.year}"
}

fun getDayAndMonth(dateInput: String): LocalDate {
    val dateOutput = dateInput.substringBefore(',')
        .plus(" 1970")
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    return LocalDate.parse(dateOutput, formatter)
}