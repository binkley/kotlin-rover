package hm.binkley.rover.mathy

data class Rotation(
    val a: Int,
    val b: Int,
    val c: Int,
    val d: Int,
)

val Rotation.scaling get() = a * d

operator fun Rotation.times(direction: Direction): Direction {
    return Direction(
        a * direction.x + b * direction.y,
        c * direction.x + d * direction.y,
    )
}
