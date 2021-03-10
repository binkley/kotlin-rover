package hm.binkley.rover.oopy

import hm.binkley.rover.space

data class Program(
    private val maxX: Int,
    private val maxY: Int,
    private val line: IndexedValue<String>,
) {
    fun check(at: Position) {
        if (maxX < at.x) throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
        if (maxY < at.y) throw IllegalArgumentException("Line #${line.index}: Malformed input: ${line.value}")
    }

    fun next(line: IndexedValue<String>) = Program(maxX, maxY, line)
}

fun IndexedValue<String>.toBoundary(): Program {
    val q = value.split(space)
    when (q.size) {
        2 -> {
            val (x, y) = q
            return Program(x.toInt(), y.toInt(), this)
        }
        else -> throw IllegalArgumentException("Line #$index: Malformed input: $value: Boundary must have 2 elements")
    }
}
