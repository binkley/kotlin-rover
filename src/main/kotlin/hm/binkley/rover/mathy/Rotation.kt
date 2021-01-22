package hm.binkley.rover.mathy

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

    fun left() = values()[(ordinal + 1) % 4]
    fun right() = values()[(ordinal + 3) % 4]

    fun prod(at: Position) =
        Position.at(a * at.x + b * at.y, c * at.x + d * at.y)
}
