package hm.binkley.rover.mathy;

import lombok.RequiredArgsConstructor;

/**
 * {@code Rotation} <b>needs documentation</b>.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 * @todo Needs documentation.
 */
@RequiredArgsConstructor
public enum Rotation {
    E(1, 0, 0, 1),
    N(0, -1, 1, 0),
    W(-1, 0, 0, -1),
    S(0, 1, -1, 0);

    private final int a, b, c, d;

    public Rotation left() {
        return values()[(ordinal() + 1) % 4];
    }

    public Rotation right() {
        return values()[(ordinal() + 3) % 4];
    }

    public Position prod(final Position at) {
        return Position.at(a * at.x() + b * at.y(), c * at.x() + d * at.y());
    }
}
