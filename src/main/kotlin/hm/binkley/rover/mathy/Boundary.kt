package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Boundary(val x: Distance, val y: Distance)

fun InputLine.toBoundary(): Boundary {
    val lexed = space.split(data)
    return when (lexed.size) {
        2 -> Boundary(
            lexed[0].toDistance(),
            lexed[1].toDistance(),
        )
        else -> throw MalformedInputException("Not a boundary: $data")
    }
}

fun Boundary.contains(x: Distance, y: Distance) {
    if (this.x < x || this.y < y)
        throw MalformedInputException("Out of bounds: $this: ($x, $y)")
}
