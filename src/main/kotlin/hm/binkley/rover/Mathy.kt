package hm.binkley.rover

import hm.binkley.rover.mathy.Path
import hm.binkley.rover.mathy.follow
import hm.binkley.rover.mathy.path

/**
 * `MathMain` is a more OOP-y approach to the Rover problem based on
 * [matrix form](https://en.wikipedia.org/wiki/Rotation_matrix) of
 * [the circle group](https://en.wikipedia.org/wiki/Circle_group).
 *
 * See [Mars Rover in Python and Haskell](http://arunrocks.com/mars-rover-in-python-and-haskell/).
 */
object Mathy {
    @JvmStatic
    fun main(vararg args: String) =
        inputLines().chunked(2).forEach { (startAt, instructions) ->
            var path = path(startAt)
            instructions.forEach { path = it.from(path) }
            println(path)
        }
}

private fun Char.from(path: Path) = path.next(follow(toString()))
