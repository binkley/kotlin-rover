package hm.binkley.rover

import java.lang.System.out

private var space = " ".toRegex()

/**
 * `CStyleMain` is a very "C"-like approach to the Rover problem.
 *
 * See [Mars Rover in Python and Haskell](http://arunrocks.com/mars-rover-in-python-and-haskell/).
 */
object CStyle {
    @JvmStatic
    fun main(vararg args: String) {
        // TODO: Stream data, not gobble all at once
        val linesStream: Iterable<IndexedValue<String>> = inputLines()
        val lines = linesStream.toList()
        var i = 0
        val l = lines.size
        while (i < l) {
            val co = lines[i].value
            val coords = parseCoordinates(co)
            var x = coords[0].toInt()
            var y = coords[1].toInt()
            var d = "ENWS".indexOf(coords[2][0])
            val mo = lines[++i].value
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

private fun parseCoordinates(line: String): List<String> {
    val coords = space.split(line)
    return when (coords.size) {
        3 -> coords
        else -> throw IllegalArgumentException("Malformed input: $line")
    }
}
