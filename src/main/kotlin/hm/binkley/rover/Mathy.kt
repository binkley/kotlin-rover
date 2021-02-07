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
    fun main(vararg args: String) {
        input().chunked(2)
            .forEach {
                var path = path(it[0])
                it[1].forEach {
                    path = it.guide(path)
                }
                println(path)
            }
    }
}

private fun Char.guide(path: Path) = path.next(follow(toString()))
