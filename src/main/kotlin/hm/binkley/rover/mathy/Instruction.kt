package hm.binkley.rover.mathy

enum class Instruction {
    L {
        override fun rotation(facing: Rotation, at: Position): Rotation =
            facing.left()

        override fun position(facing: Rotation, at: Position): Position =
            at
    },
    R {
        override fun rotation(facing: Rotation, at: Position): Rotation =
            facing.right()

        override fun position(facing: Rotation, at: Position): Position =
            at
    },
    M {
        override fun rotation(facing: Rotation, at: Position): Rotation =
            facing

        override fun position(facing: Rotation, at: Position): Position =
            at.forward(facing)
    },
    B {
        override fun rotation(facing: Rotation, at: Position): Rotation =
            facing

        override fun position(facing: Rotation, at: Position): Position =
            at.back(facing)
    };

    abstract fun rotation(
        facing: Rotation,
        at: Position,
    ): Rotation

    abstract fun position(
        facing: Rotation,
        at: Position,
    ): Position
}

fun follow(ins: String) = Instruction.valueOf(ins)
