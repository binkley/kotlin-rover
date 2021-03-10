package hm.binkley.rover.mathy

import hm.binkley.rover.MalformedInputException

data class Direction(val x: Int, val y: Int) {
    override fun toString() =
        when (this) {
            N -> "N"
            W -> "W"
            S -> "S"
            E -> "E"
            else -> "($x, $y)"
        }
}

val N = Direction(0, 1)
val W = Direction(-1, 0)
val S = Direction(0, -1)
val E = Direction(1, 0)

fun String.toDirection() = when (this) {
    "N" -> N
    "W" -> W
    "S" -> S
    "E" -> E
    else -> throw MalformedInputException("Not a direction: $this")
}

operator fun Rotation.times(direction: Direction): Direction {
    return Direction(
        a * direction.x + b * direction.y,
        c * direction.x + d * direction.y,
    )
}
