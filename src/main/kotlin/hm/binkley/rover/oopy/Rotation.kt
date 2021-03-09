package hm.binkley.rover.oopy

enum class Rotation(
    private val a: Int,
    private val b: Int,
    private val c: Int,
    private val d: Int,
) {
    E(1, 0, 0, 1),
    N(0, -1, 1, 0),
    W(-1, 0, 0, -1),
    S(0, 1, -1, 0);

    operator fun times(other: Position) =
        at(a * other.x + b * other.y, c * other.x + d * other.y)
}

fun rotate(rot: String) = Rotation.valueOf(rot)

fun Rotation.left() = this + 1
fun Rotation.right() = this + 3

operator fun Rotation.plus(n: Int) = Rotation.values()[(ordinal + n) % 4]
