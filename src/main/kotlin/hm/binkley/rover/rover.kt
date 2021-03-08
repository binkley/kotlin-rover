package hm.binkley.rover

/**
 * Creates a stream of input lines indexed by input line number.
 *
 * The "index" property is suitable for reporting input line numbers
 */
internal fun inputLines(): Iterable<IndexedValue<String>> =
    generateSequence(::readLine)
        .toList()
        .withIndex()
        .map {
            // Present 1-based indexing for line numbers, not 0-based
            IndexedValue(it.index + 1, it.value)
        }

internal val space = " ".toRegex()
