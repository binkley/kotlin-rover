package hm.binkley.rover.mathy

import hm.binkley.rover.MalformedInputException

enum class Instruction(val rotation: Rotation) {
    M(Rotation(1, 0, 0, 1)),
    L(Rotation(0, -1, 1, 0)),
    B(Rotation(-1, 0, 0, -1)),
    R(Rotation(0, 1, -1, 0)),
}

fun String.toInstruction() = try {
    Instruction.valueOf(this)
} catch (e: IllegalArgumentException) {
    throw MalformedInputException("Not an instruction: $this", e)
}

operator fun Instruction.invoke(
    position: Position,
    boundary: Boundary,
): Position {
    // TODO: Combine rotating and scaling into single op or fun
    val newFacing = rotation * position.facing
    val (addX, addY) = rotation.scaling * newFacing
    val newX = position.x + addX.toDistance()
    val newY = position.y + addY.toDistance()

    // TODO: Too intimate: Put this check in one place only
    boundary.contains(newX, newY)

    return Position(newX, newY, newFacing)
}

private operator fun Int.times(direction: Direction) =
    Direction(this * direction.x, this * direction.y)
