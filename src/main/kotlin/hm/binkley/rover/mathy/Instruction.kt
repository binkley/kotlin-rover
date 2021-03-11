package hm.binkley.rover.mathy

import hm.binkley.rover.MalformedInputException

enum class Instruction(val rotation: Rotation, val move: Int) {
    M(Rotation(1, 0, 0, 1), 1), // No rotation
    L(Rotation(0, -1, 1, 0), 0),
    B(Rotation(1, 0, 0, 1), -1), // No rotation
    R(Rotation(0, 1, -1, 0), 0),
}

fun String.toInstruction() = try {
    Instruction.valueOf(this)
} catch (_: IllegalArgumentException) {
    throw MalformedInputException("Not an instruction: $this")
}

operator fun Instruction.invoke(
    position: Position,
    boundary: Boundary,
): Position {
    // TODO: Combine rotating and scaling into single op or fun
    val newFacing = rotation * position.facing
    val direction = rotation.scaling * newFacing
    val (addX, addY) = move * direction
    val newX = position.x + addX.toDistance()
    val newY = position.y + addY.toDistance()

    // TODO: Too intimate: Put this check in one place only
    boundary.contains(newX, newY)

    return Position(newX, newY, newFacing)
}

private operator fun Int.times(direction: Direction) =
    Direction(this * direction.x, this * direction.y)
