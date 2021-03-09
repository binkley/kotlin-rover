package hm.binkley.rover.mathy

data class Direction(val x: Int, val y: Int)

private val N = Direction(0, 1)
private val W = Direction(-1, 0)
private val S = Direction(0, -1)
private val E = Direction(1, 0)

fun String.toDirection(invalid: () -> Nothing) = when (this) {
    "N" -> N
    "W" -> W
    "S" -> S
    "E" -> E
    else -> invalid()
}

operator fun Rotation.times(direction: Direction): Direction {
    return Direction(
        a * direction.x + b * direction.y,
        c * direction.x + d * direction.y,
    )
}
