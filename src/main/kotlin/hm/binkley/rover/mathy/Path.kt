package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Path(
    private val facing: Rotation,
    private val at: Position,
    private val boundaries: Boundaries,
    private val line: IndexedValue<String>,
) {
    init {
        boundaries.check(at, line)
    }

    fun next(n: Instruction, line: IndexedValue<String>) = Path(
        n.rotation(facing, at),
        n.position(facing, at),
        boundaries,
        line
    )

    override fun toString() = "%d %d %s".format(at.x, at.y, facing)
}

fun path(line: IndexedValue<String>, boundaries: Boundaries): Path {
    val q = line.value.split(space)
    when (q.size) {
        3 -> {
            val (x, y, rot) = q
            return Path(
                rotate(rot),
                at(x.toInt(), y.toInt()),
                boundaries,
                line,
            )
        }
        else -> throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
    }
}
