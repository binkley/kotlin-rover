package hm.binkley.rover.mathy

inline class Distance(private val d: Int) {
    operator fun plus(addend: Distance) = Distance(d + addend.d)
}

fun String.toDistance(invalid: () -> Nothing) = try {
    Distance(toInt())
} catch (e: NumberFormatException) {
    invalid()
}

fun Int.toDistance() = Distance(this)