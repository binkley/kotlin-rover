package hm.binkley.rover.mathy

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

fun path(line: String): Path {
    val (x, y, rot) = line.split(space, 3)
    return Path(rotate(rot), at(x.toInt(), y.toInt()))
}
