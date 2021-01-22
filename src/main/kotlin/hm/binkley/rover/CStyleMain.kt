package hm.binkley.rover

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.System.`in`
import java.lang.System.out
import java.util.stream.Collectors.toList

/**
 * `CStyleMain` is a very "C"-like approach to the Rover problem.
 *
 * See [Mars Rover in Python and Haskell](http://arunrocks.com/mars-rover-in-python-and-haskell/).
 */
object CStyleMain {
    @JvmStatic
    fun main(vararg args: String) {
        val lines = BufferedReader(InputStreamReader(`in`))
            .lines()
            .collect(toList())
        if (0 == lines.size % 2) throw IOException("Malformed input")

        // TODO: Skip grid size - what to do with it?
        var i = 1
        val l = lines.size
        while (i < l) {
            val co = lines[i]
            val coords = co.split(" ".toRegex(), 3).toTypedArray()
            var x = coords[0].toInt()
            var y = coords[1].toInt()
            var d = "ENWS".indexOf(coords[2][0])
            val mo = lines[++i]
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
