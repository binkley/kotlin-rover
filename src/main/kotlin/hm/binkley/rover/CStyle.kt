package hm.binkley.rover

import hm.binkley.rover.c.runLittleRoverRun

/**
 * `CStyleMain` is a very "C"-like approach to the Rover problem.
 *
 * See [Mars Rover in Python and Haskell](http://arunrocks.com/mars-rover-in-python-and-haskell/).
 */
object CStyle {
    @JvmStatic
    fun main(vararg args: String) {
        runLittleRoverRun(inputLines())
    }
}
