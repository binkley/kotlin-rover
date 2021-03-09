package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Position(val x: Distance, val y: Distance, val facing: Direction)

fun InputLine.toPosition(): Position {
    val lexed = space.split(data)
    return when (lexed.size) {
        3 -> Position(
            lexed[0].toDistance { invalid() },
            lexed[1].toDistance { invalid() },
            lexed[2].toDirection { invalid() },
        )
        else -> invalid()
    }
}

fun Position.execute(instruction: Instruction) = instruction(this)
