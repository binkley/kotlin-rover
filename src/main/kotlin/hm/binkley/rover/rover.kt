package hm.binkley.rover

/**
 * Creates a stream of input lines indexed by input line number.
 *
 * The "index" property is suitable for reporting input line numbers
 */
internal fun inputLines(): Iterable<IndexedValue<String>> {
    return generateSequence(::readLine)
        .toList()
        .withIndex()
        .map {
            IndexedValue(it.index + 1, it.value)
        }
}

internal val space = " ".toRegex()
