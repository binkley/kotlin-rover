package hm.binkley.rover.mathy

import hm.binkley.rover.MalformedInputException

inline class Distance(val d: Int) {
    operator fun plus(addend: Distance) = Distance(d + addend.d)

    override fun toString() = d.toString()
}

fun String.toDistance() = try {
    Distance(toInt())
} catch (_: NumberFormatException) {
    throw MalformedInputException("Not a distance: $this")
}

fun Int.toDistance() = Distance(this)

operator fun Distance.compareTo(other: Distance) = d.compareTo(other.d)
