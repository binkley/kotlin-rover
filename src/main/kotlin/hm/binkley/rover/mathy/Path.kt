package hm.binkley.rover.mathy

import hm.binkley.rover.mathy.Position.Companion.at

class Path private constructor(
    private val facing: Rotation,
    private val at: Position,
) {
    fun next(n: Instruction): Path {
        return Path(
            n.rotation(facing, at),
            n.position(
                facing,
                at
            )
        )
    }

    override fun toString(): String {
        return "%d %d %s".format(at.x, at.y, facing)
    }

    companion object {
        fun path(line: String): Path {
            val coords = line.split(" ".toRegex(), 3).toTypedArray()
            return Path(
                Rotation.valueOf(
                    coords[2]
                ),
                at(coords[0].toInt(), coords[1].toInt())
            )
        }
    }
}
