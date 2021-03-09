package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Boundary(val x: Distance, val y: Distance)

fun InputLine.toBoundary(): Boundary {
    val lexed = space.split(data)
    return when (lexed.size) {
        2 -> Boundary(
            lexed[0].toDistance { invalid() },
            lexed[1].toDistance { invalid() },
        )
        else -> invalid()
    }
}
