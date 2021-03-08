package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Boundaries(private val maxX: Int, private val maxY: Int) {
    fun check(at: Position, line: IndexedValue<String>) {
        if (maxX < at.x) throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
        if (maxY < at.y) throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
    }
}

fun boundaries(line: IndexedValue<String>): Boundaries {
    val q = line.value.split(space)
    when (q.size) {
        2 -> {
            val (x, y) = q
            return Boundaries(x.toInt(), y.toInt())
        }
        else -> throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
    }
}
