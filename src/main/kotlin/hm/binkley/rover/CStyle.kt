package hm.binkley.rover

import hm.binkley.rover.mathy.consumeBoundaries
import java.lang.System.out

/**
 * `CStyleMain` is a very "C"-like approach to the Rover problem.
 *
 * See [Mars Rover in Python and Haskell](http://arunrocks.com/mars-rover-in-python-and-haskell/).
 */
object CStyle {
    @JvmStatic
    fun main(vararg args: String) {
        // TODO: Stream data, not gobble all at once
        val indexedLines = inputLines().toMutableList()

        consumeBoundaries(indexedLines.removeAt(0))

        var i = 0
        val l = indexedLines.size
        while (i < l) {
            val co = indexedLines[i]
            val coords = parseStartingPosition(co)
            var x = coords[0].toInt()
            var y = coords[1].toInt()
            var d = "ENWS".indexOf(coords[2][0])
            val mo = indexedLines[++i].value
            var j = 0
            val k = mo.length

            while (j < k) {
                when (mo[j]) {
                    'L' -> d += 1
                    'R' -> d += 3
                    'M' -> when (d % 4) {
                        0 -> ++x
                        1 -> ++y
                        2 -> --x
                        3 -> --y
                    }
                }
                ++j
            }
            out.printf("%d %d %c%n", x, y, "ENWS"[d % 4])
            ++i
        }
    }
}

private fun parseStartingPosition(indexedLine: IndexedValue<String>): List<String> {
    val coords = space.split(indexedLine.value)
    return when (coords.size) {
        3 -> coords
        else -> throw IllegalArgumentException("Line #${indexedLine.index}: Malformed input: ${indexedLine.value}")
    }
}
