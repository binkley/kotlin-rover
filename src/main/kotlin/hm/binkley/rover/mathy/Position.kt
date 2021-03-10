package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Position(val x: Distance, val y: Distance, val facing: Direction)

fun InputLine.toPosition(boundary: Boundary): Position {
    val lexed = space.split(data)
    if (lexed.size != 3) invalid()
    val x = lexed[0].toDistance()
    val y = lexed[1].toDistance()

    boundary.contains(x, y)

    return Position(
        x, y,
        facing = lexed[2].toDirection(),
    )
}
