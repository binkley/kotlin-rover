package hm.binkley.rover

import hm.binkley.rover.mathy.Instruction
import hm.binkley.rover.mathy.path
import hm.binkley.rover.util.PairIterable.Companion.pairsOf
import hm.binkley.rover.util.StringIterable.Companion.over
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.stream.Collectors

/**
 * `MathMain` is a more OOP-y approach to the Rover problem based on
 * [matrix form](https://en.wikipedia.org/wiki/Rotation_matrix) of
 * [the circle group](https://en.wikipedia.org/wiki/Circle_group).
 *
 * See [Mars Rover in Python and Haskell](http://arunrocks.com/mars-rover-in-python-and-haskell/).
 */
object MathyMain {
    @JvmStatic
    fun main(vararg args: String) {
        val lines = BufferedReader(InputStreamReader(System.`in`))
            .lines()
            .collect(Collectors.toList())
        if (0 == lines.size % 2) throw IOException("Malformed input")

        // TODO: Skip grid size - what to do with it?
        for (p in pairsOf(lines.subList(1, lines.size))) {
            var path = path(p.a)
            for (n in over(p.b)) path = path.next(Instruction.valueOf(n))
            println(path)
        }
    }
}
