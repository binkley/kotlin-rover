package hm.binkley.rover.mathy

data class Position(val x: Int, val y: Int) {
    fun neg() = Position(-x, -y)
    fun forward(facing: Rotation) = add(facing.prod(E0))
    fun back(facing: Rotation) = add(facing.prod(E0).neg())
    fun add(that: Position) = at(x + that.x, y + that.y)

    companion object {
        private val E0 = at(1, 0)

        fun at(x: Int, y: Int) = Position(x, y)
    }
}
