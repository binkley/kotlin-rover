package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Path(
    private val facing: Rotation,
    private val at: Position,
) {
    fun next(n: Instruction) = Path(
        n.rotation(facing, at),
        n.position(facing, at)
    )

    override fun toString() = "%d %d %s".format(at.x, at.y, facing)
}

fun path(line: IndexedValue<String>): Path {
    val q = line.value.split(space)
    when (q.size) {
        3 -> {
            val (x, y, rot) = q
            return Path(rotate(rot), at(x.toInt(), y.toInt()))
        }
        else -> throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
    }
}
