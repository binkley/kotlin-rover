package hm.binkley.rover.mathy

import hm.binkley.rover.space

data class Boundaries(val x: Int, val y: Int)

fun consumeBoundaries(line: IndexedValue<String>) {
    val q = line.value.split(space)
    when (q.size) {
        2 -> {
            val (x, y) = q
            Boundaries(x.toInt(), y.toInt())
        }
        else -> throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
    }
}
