package hm.binkley.rover

fun input(): List<String> {
    // TODO: Stream input, rather than suck it all in up front
    val lines = generateSequence(::readLine).toList()

    // TODO: Skip grid size - what to do with it?
    require(1 == lines.size % 2) { "Malformed input" }
    return lines.drop(1)
}
