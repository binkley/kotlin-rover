package hm.binkley.rover.mathy

data class InputLine(
    val number: Int,
    val data: String,
)

fun InputLine.invalid(): Nothing =
    throw IllegalArgumentException("Line #$number: Malformed input: $data")

internal fun inputLines(): Sequence<InputLine> =
    generateSequence(::readLine)
        .withIndex()
        .map {
            // Present 1-based indexing for line numbers, not 0-based
            InputLine(it.index + 1, it.value)
        }
