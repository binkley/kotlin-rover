package hm.binkley.rover

import hm.binkley.rover.c.runLittleRoverRun

/** `CStyle` is a very "C"-like approach to the Rover problem. */
object CStyle {
    @JvmStatic
    fun main(vararg args: String) {
        runLittleRoverRun(inputLines())
    }
}
