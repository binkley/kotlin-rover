package hm.binkley.rover.mathy;

import static hm.binkley.rover.mathy.Position.at;
import static java.lang.String.format;

/**
 * {@code Path} <strong>needs documentation</strong>.
 *
 * @author <a href="mailto:boxley@thoughtworks.com">Brian Oxley</a>
 * @todo Needs documentation
 */
public final class Path {
    private final Rotation facing;
    private final Position at;

    public static Path path(final String line) {
        final var coords = line.split(" ", 3);

        return new Path(Rotation.valueOf(coords[2]),
                at(Integer.parseInt(coords[0]), Integer.parseInt(coords[1])));
    }

    private Path(final Rotation facing, final Position at) {
        this.facing = facing;
        this.at = at;
    }

    public Path next(final Instruction n) {
        return new Path(n.rotation(facing, at), n.position(facing, at));
    }

    @Override
    public String toString() {
        return format("%d %d %s", at.x(), at.y(), facing);
    }
}
