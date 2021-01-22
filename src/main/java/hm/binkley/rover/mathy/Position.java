package hm.binkley.rover.mathy;

import lombok.RequiredArgsConstructor;

/**
 * {@code Position} <b>needs documentation</b>.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 * @todo Needs documentation.
 */
@RequiredArgsConstructor
public final class Position {
    private static final Position E0 = at(1, 0);

    private final int x, y;

    public static Position at(final int x, final int y) {
        return new Position(x, y);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Position neg() {
        return new Position(-x, -y);
    }

    public Position forward(final Rotation facing) {
        return add(facing.prod(E0));
    }

    public Position back(final Rotation facing) {
        return add(facing.prod(E0).neg());
    }

    public Position add(final Position that) {
        return at(x + that.x, y + that.y);
    }
}
