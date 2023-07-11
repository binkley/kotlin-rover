package hm.binkley.rover.mathy

import hm.binkley.rover.MalformedInputException

data class InputLine(
    val number: Int,
    val data: String,
)

internal fun InputLine.invalid(
    message: String? = null,
    cause: Exception? = null,
): Nothing {
    // TODO: Clean up this mess
    if (null == message) {
        if (null == cause) {
            throw IllegalArgumentException(
                "Line #$number: Malformed input: $data"
            )
        } else {
            when (cause) {
                is MalformedInputException -> throw IllegalArgumentException(
                    "Line #$number: Malformed input: $data: ${cause.message}"
                )

                else -> throw IllegalArgumentException(
                    "Line #$number: Malformed input: $data: $cause"
                )
            }
        }
    } else if (null == cause) {
        throw IllegalArgumentException(
            "Line #$number: Malformed input: $data: $message"
        )
    } else {
        when (cause) {
            is MalformedInputException -> throw IllegalArgumentException(
                "Line #$number: Malformed input: $data:" +
                    " $message: ${cause.message}"
            )

            else -> throw IllegalArgumentException(
                "Line #$number: Malformed input: $data: $message: $cause"
            )
        }
    }
}

internal fun inputLines(): Sequence<InputLine> =
    generateSequence(::readLine)
        .withIndex()
        .map {
            // Present 1-based indexing for line numbers, not 0-based
            InputLine(it.index + 1, it.value)
        }
