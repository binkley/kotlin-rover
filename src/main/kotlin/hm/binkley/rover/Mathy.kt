package hm.binkley.rover

import hm.binkley.rover.mathy.inputLines
import hm.binkley.rover.mathy.invoke
import hm.binkley.rover.mathy.toBoundary
import hm.binkley.rover.mathy.toPath
import hm.binkley.rover.mathy.toPosition

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
        // TODO: Stream, not convert to list
        val lines = inputLines().toMutableList()
        val firstLine = lines.removeAt(0)
        /* val boundary = */ firstLine.toBoundary()
        lines.chunked(2).forEach { (startAt, instructions) ->
            // Could use fold here, but this seems more readable to me
            var position = startAt.toPosition()
            val path = instructions.toPath()
            path.forEach { position = it(position) }
            println("${position.x} ${position.y} ${position.facing}")
        }
    }
}
