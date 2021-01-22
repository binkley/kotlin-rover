package hm.binkley.rover.util

class StringIterable(
    private val s: String,
) : Iterable<String> {
    private val x = s.length

    override fun iterator(): Iterator<String> {
        return object : Iterator<String> {
            private var i = 0

            override fun hasNext() = i < x
            override fun next() = s[i++].toString()
        }
    }

    companion object {
        fun over(s: String) = StringIterable(s)
    }
}
