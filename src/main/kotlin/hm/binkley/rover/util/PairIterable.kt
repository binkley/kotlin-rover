package hm.binkley.rover.util

import hm.binkley.rover.util.PairIterable.Pair
import java.util.NoSuchElementException

class PairIterable<T>(
    private val delegate: Iterable<T>,
) : Iterable<Pair<T>> {
    data class Pair<T>(val a: T, val b: T)

    override fun iterator(): Iterator<Pair<T>> {
        return PairIterator(delegate.iterator())
    }

    private class PairIterator<T>(
        private val it: Iterator<T>,
    ) : Iterator<Pair<T>> {
        override fun hasNext(): Boolean {
            return it.hasNext()
        }

        override fun next(): Pair<T> {
            val a = it.next()
            if (!it.hasNext()) throw NoSuchElementException("Not a pair")
            return Pair(a, it.next())
        }
    }

    companion object {
        fun <T> pairsOf(delegate: Iterable<T>): PairIterable<T> {
            return PairIterable(delegate)
        }
    }
}
