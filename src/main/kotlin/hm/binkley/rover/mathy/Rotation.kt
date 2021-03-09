package hm.binkley.rover.mathy

data class Rotation(
    val a: Int,
    val b: Int,
    val c: Int,
    val d: Int,
)

val Rotation.scaling get() = a * d
