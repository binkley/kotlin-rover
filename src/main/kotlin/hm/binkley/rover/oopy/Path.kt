package hm.binkley.rover.oopy

import hm.binkley.rover.mathy.InputLine
import hm.binkley.rover.space

data class Path(
    private val facing: Rotation,
    private val at: Position,
    private val program: Program,
) {
    init {
        program.check(at)
    }

    fun next(n: Instruction, line: IndexedValue<String>) = Path(
        n.rotation(facing, at),
        n.position(facing, at),
        program.next(line),
    )

    override fun toString() = "%d %d %s".format(at.x, at.y, facing)
}

fun IndexedValue<String>.toPath(program: Program): Path {
    val q = value.split(space)
    when (q.size) {
        3 -> {
            val (x, y, rot) = q
            return Path(
                readRotation(rot),
                at(x.toInt(), y.toInt()),
                program.next(this),
            )
        }
        else -> throw IllegalArgumentException("Line #$index: Malformed input: $value: Position must have 3 elements")
    }
}

private fun IndexedValue<String>.readRotation(rot: String) =
    try {
        rotate(rot)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Line #$index: Malformed input: $value: Not a direction: $rot")
    }
