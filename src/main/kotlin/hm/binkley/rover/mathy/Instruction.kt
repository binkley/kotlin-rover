package hm.binkley.rover.mathy

enum class Instruction(val rotation: Rotation) {
    M(Rotation(1, 0, 0, 1)),
    L(Rotation(0, -1, 1, 0)),
    B(Rotation(-1, 0, 0, -1)),
    R(Rotation(0, 1, -1, 0)),
}

fun String.toInstruction(invalid: () -> Nothing) = try {
    Instruction.valueOf(this)
} catch (e: IllegalArgumentException) {
    invalid()
}

operator fun Instruction.invoke(position: Position): Position {
    val newFacing = rotation * position.facing
    val scaling = rotation.scaling
    val (addX, addY) = scaling * newFacing
    return Position(
        position.x + addX.toDistance(),
        position.y + addY.toDistance(),
        newFacing
    )
}

private operator fun Int.times(direction: Direction) =
    Direction(this * direction.x, this * direction.y)
