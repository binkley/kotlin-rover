package hm.binkley.rover

/**
 * Creates a lazy stream of input lines indexed by input line number.
 *
 * The "index" property is suitable for reporting input line numbers (1-based)
 */
internal fun inputLines(): Sequence<IndexedValue<String>> =
    generateSequence(::readLine)
        .withIndex()
        .map {
            // Present 1-based indexing for line numbers, not 0-based
            IndexedValue(it.index + 1, it.value)
        }

internal val space = " ".toRegex()
