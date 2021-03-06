package hm.binkley.rover.mathy

import lombok.Generated

private val space = " ".toRegex()

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

@Generated // TODO: Remove this once use cases are fully solid
fun path(line: String): Path {
    val q = line.split(space)
    when (q.size) {
        3 -> {
            val (x, y, rot) = q
            return Path(rotate(rot), at(x.toInt(), y.toInt()))
        }
        else -> throw IllegalArgumentException("Malformed input: $line")
    }
}
