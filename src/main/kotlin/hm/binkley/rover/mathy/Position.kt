package hm.binkley.rover.mathy

data class Position(val x: Int, val y: Int)

private val E0 = at(1, 0)

fun at(x: Int, y: Int) = Position(x, y)

fun Position.forward(facing: Rotation) = this + facing * E0
fun Position.back(facing: Rotation) = this - facing * E0

operator fun Position.unaryMinus() = at(-x, -y)
operator fun Position.plus(other: Position) = at(x + other.x, y + other.y)
operator fun Position.minus(other: Position) = this + -other
