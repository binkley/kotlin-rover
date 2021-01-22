package hm.binkley.rover.util;

import java.util.Iterator;

/**
 * {@code StringIterable} <strong>needs documentation</strong>.
 *
 * @author <a href="mailto:boxley@thoughtworks.com">Brian Oxley</a>
 * @todo Needs documentation
 */
public final class StringIterable
        implements Iterable<String> {
    private final String s;
    private final int x;

    public static StringIterable over(final String s) {
        return new StringIterable(s);
    }

    private StringIterable(final String s) {
        this.s = s;
        x = s.length();
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int i;

            @Override
            public boolean hasNext() {
                return i < x;
            }

            @Override
            public String next() {
                return String.valueOf(s.charAt(i++));
            }
        };
    }
}
