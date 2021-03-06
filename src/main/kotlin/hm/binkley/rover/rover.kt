package hm.binkley.rover

fun inputLines(): Iterable<IndexedValue<String>> {
    val lines = generateSequence(::readLine).toList()

    // TODO: Skip grid size - what to do with it?
    require(1 == lines.size % 2) { "Malformed input" }
    return lines.drop(1).withIndex()
}
