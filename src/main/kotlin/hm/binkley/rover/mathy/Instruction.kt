package hm.binkley.rover.mathy

enum class Instruction(
    private val spin: (Rotation, Position) -> Rotation,
    private val move: (Rotation, Position) -> Position,
) {
    L({ facing, _ -> facing.left() }, { _, at -> at }),
    R({ facing, _ -> facing.right() }, { _, at -> at }),
    M({ facing, _ -> facing }, { facing, at -> at.forward(facing) }),
    B({ facing, _ -> facing }, { facing, at -> at.back(facing) });

    fun rotation(facing: Rotation, at: Position): Rotation = spin(facing, at)
    fun position(facing: Rotation, at: Position): Position = move(facing, at)
}

fun follow(input: String) = Instruction.valueOf(input)
