package model

import java.time.LocalDate

data class Flight(private val source: String, private val destination: String, private val date: LocalDate) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Flight

        if (source != other.source) return false
        if (destination != other.destination) return false
        if (date.dayOfMonth != other.date.dayOfMonth && date.monthValue == other.date.monthValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + destination.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}