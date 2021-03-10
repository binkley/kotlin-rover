package hm.binkley.rover

import hm.binkley.rover.mathy.Boundary
import hm.binkley.rover.mathy.InputLine
import hm.binkley.rover.mathy.Instruction
import hm.binkley.rover.mathy.MalformedInputException
import hm.binkley.rover.mathy.Path
import hm.binkley.rover.mathy.Position
import hm.binkley.rover.mathy.inputLines
import hm.binkley.rover.mathy.invalid
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
        val boundary = readBoundary(lines)
        lines.chunked(2).forEach { (startAt, instructions) ->
            // Could use fold here, but this seems more readable to me
            var position = readStartAt(startAt, boundary)
            val path = readPath(instructions)
            path.forEach { instruction ->
                position =
                    instructions.execute(instruction, position, boundary)
            }
            println("${position.x} ${position.y} ${position.facing}")
        }
    }
}

private fun readBoundary(lines: MutableList<InputLine>): Boundary {
    val firstLine = lines.removeAt(0)
    try {
        return firstLine.toBoundary()
    } catch (e: MalformedInputException) {
        firstLine.invalid()
    }
}

private fun readStartAt(
    startAt: InputLine,
    boundary: Boundary,
): Position = try {
    startAt.toPosition(boundary)
} catch (e: MalformedInputException) {
    startAt.invalid()
}

private fun readPath(instructions: InputLine): Path = try {
    instructions.toPath()
} catch (e: MalformedInputException) {
    instructions.invalid()
}

private fun InputLine.execute(
    instruction: Instruction,
    position: Position,
    boundary: Boundary,
) = try {
    instruction(position, boundary)
} catch (e: MalformedInputException) {
    invalid()
}
