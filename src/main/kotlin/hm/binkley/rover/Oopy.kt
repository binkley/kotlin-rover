package hm.binkley.rover

import hm.binkley.rover.oopy.Path
import hm.binkley.rover.oopy.follow
import hm.binkley.rover.oopy.toBoundary
import hm.binkley.rover.oopy.toPath

/**
 * `MathMain` is a more OOP-y approach to the Rover problem based on
 * [matrix form](https://en.wikipedia.org/wiki/Rotation_matrix) of
 * [the circle group](https://en.wikipedia.org/wiki/Circle_group).
 *
 * See [Mars Rover in Python and Haskell](http://arunrocks.com/mars-rover-in-python-and-haskell/).
 */
object Oopy {
    @JvmStatic
    fun main(vararg args: String) {
        val lines = inputLines().toMutableList()
        val boundary = lines.removeAt(0).toBoundary()
        lines.chunked(2).forEach { (startAt, instructions) ->
            // Could use fold here, but this seems more readable to me
            var path = startAt.toPath(boundary)
            instructions.value.forEach { path = it.readWith(path, instructions) }
            println(path)
        }
    }
}

// TODO: These functions are awkward -- what should "this" be?
// TODO: Hide "Char" lower in callstack as an implementation detail

private fun Char.readWith(path: Path, line: IndexedValue<String>) =
    path.next(line.readInstruction(this), line)

private fun IndexedValue<String>.readInstruction(ins: Char) =
    try {
        follow(ins.toString())
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Line #$index: Malformed input: $value: Not an instruction: $ins")
    }
