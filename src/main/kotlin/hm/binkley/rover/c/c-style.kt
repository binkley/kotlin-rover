package hm.binkley.rover.c

import hm.binkley.rover.space
import java.lang.System.out

fun runLittleRoverRun(input: Sequence<IndexedValue<String>>) {
    // TODO: Stream data, not gobble all at once
    val indexedLines = input.toMutableList()

    val (maxX, maxY) = parseBoundaries(indexedLines[0])

    var i = 1
    val l = indexedLines.size
    while (i < l) {
        val co = indexedLines[i]
        val mo = indexedLines[++i]

        val coords = parseStartingPosition(co)
        var x = coords[0].toInt()
        var y = coords[1].toInt()
        var d = "ENWS".indexOf(coords[2][0])

        if (maxX < x) throw IllegalArgumentException("Line #${co.index}: Malformed input: ${co.value}")
        if (maxY < y) throw IllegalArgumentException("Line #${co.index}: Malformed input: ${co.value}")

        val ins = mo.value
        var j = 0
        val k = ins.length

        while (j < k) {
            when (ins[j]) {
                'L' -> d += 1
                'R' -> d += 3
                'M' -> when (d % 4) {
                    0 -> ++x
                    1 -> ++y
                    2 -> --x
                    3 -> --y
                }
                'B' -> when (d % 4) {
                    0 -> --x
                    1 -> --y
                    2 -> ++x
                    3 -> ++y
                }
                else -> throw IllegalArgumentException("Line #${mo.index}: Malformed input: $ins")
            }

            if (maxX < x) throw IllegalArgumentException("Line #${mo.index}: Malformed input: $ins")
            if (maxY < y) throw IllegalArgumentException("Line #${mo.index}: Malformed input: $ins")

            ++j
        }

        out.printf("%d %d %c%n", x, y, "ENWS"[d % 4])
        ++i
    }
}

private fun parseBoundaries(indexedLine: IndexedValue<String>): List<Int> {
    val boundaries = space.split(indexedLine.value)
    return when (boundaries.size) {
        2 -> {
            val (x, y) = boundaries
            listOf(x.toInt(), y.toInt())
        }
        else -> throw IllegalArgumentException("Line #${indexedLine.index}: Malformed input: ${indexedLine.value}")
    }
}

private fun parseStartingPosition(indexedLine: IndexedValue<String>): List<String> {
    val coords = space.split(indexedLine.value)
    return when (coords.size) {
        3 -> coords
        else -> throw IllegalArgumentException("Line #${indexedLine.index}: Malformed input: ${indexedLine.value}")
    }
}
