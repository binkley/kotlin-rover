package hm.binkley.rover

import hm.binkley.rover.mathy.Boundary
import hm.binkley.rover.mathy.InputLine
import hm.binkley.rover.mathy.Position
import hm.binkley.rover.mathy.execute
import hm.binkley.rover.mathy.inputLines
import hm.binkley.rover.mathy.invalid
import hm.binkley.rover.mathy.toBoundary
import hm.binkley.rover.mathy.toPath
import hm.binkley.rover.mathy.toPosition

/**
 * `Mathy` is a more OOP-y approach to the Rover problem based on
 * [matrix form](https://en.wikipedia.org/wiki/Rotation_matrix) of
 * [the circle group](https://en.wikipedia.org/wiki/Circle_group).
 */
object Mathy {
    @JvmStatic
    fun main(vararg args: String) {
        // TODO: Stream, not convert to list
        val lines = inputLines().toMutableList()
        val boundary = lines.readBoundary()
        lines.chunked(2).forEach { (startAt, instructions) ->
            val position = startAt.readWith(boundary)
            val endAt = instructions.readWithAndExecute(position, boundary)

            println("${endAt.x} ${endAt.y} ${endAt.facing}")
        }
    }
}

private fun MutableList<InputLine>.readBoundary(): Boundary {
    val firstLine = removeAt(0)
    try {
        return firstLine.toBoundary()
    } catch (e: MalformedInputException) {
        firstLine.invalid(cause = e)
    }
}

private fun InputLine.readWith(
    boundary: Boundary,
): Position = try {
    toPosition(boundary)
} catch (e: MalformedInputException) {
    invalid(cause = e)
}

private fun InputLine.readWithAndExecute(
    position: Position,
    boundary: Boundary,
) = try {
    toPath().execute(position, boundary)
} catch (e: MalformedInputException) {
    invalid(cause = e)
}
