package hm.binkley.rover.util;

import hm.binkley.rover.util.PairIterable.Pair;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static lombok.AccessLevel.PRIVATE;

/**
 * {@code PairIterable} <strong>needs documentation</strong>.
 *
 * @author <a href="mailto:boxley@thoughtworks.com">Brian Oxley</a>
 * @todo Needs documentation
 */
@RequiredArgsConstructor(access = PRIVATE)
public final class PairIterable<T>
        implements Iterable<Pair<T>> {
    @RequiredArgsConstructor(access = PRIVATE)
    public static final class Pair<T> {
        public final T a;
        public final T b;
    }

    public static <T> PairIterable<T> pairsOf(final Iterable<T> delegate) {
        return new PairIterable<>(delegate);
    }

    private final Iterable<T> delegate;

    @Override
    public Iterator<Pair<T>> iterator() {
        return new PairIterator<>(delegate.iterator());
    }

    private static final class PairIterator<T>
            implements Iterator<Pair<T>> {
        private final Iterator<T> it;

        private PairIterator(final Iterator<T> it) {
            this.it = it;
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Pair<T> next() {
            final T a = it.next();
            if (!it.hasNext())
                throw new NoSuchElementException("Not a pair");
            return new Pair<>(a, it.next());
        }
    }
}
