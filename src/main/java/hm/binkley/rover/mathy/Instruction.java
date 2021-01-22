package hm.binkley.rover.mathy;

/**
 * {@code Instruction} <strong>needs documentation</strong>.
 *
 * @author <a href="mailto:boxley@thoughtworks.com">Brian Oxley</a>
 * @todo Needs documentation
 */
public enum Instruction {
    L {
        @Override
        public Rotation rotation(final Rotation facing, final Position at) {
            return facing.left();
        }

        @Override
        public Position position(final Rotation facing, final Position at) {
            return at;
        }
    },
    R {
        @Override
        public Rotation rotation(final Rotation facing, final Position at) {
            return facing.right();
        }

        @Override
        public Position position(final Rotation facing, final Position at) {
            return at;
        }
    },
    M {
        @Override
        public Rotation rotation(final Rotation facing, final Position at) {
            return facing;
        }

        @Override
        public Position position(final Rotation facing, final Position at) {
            return at.forward(facing);
        }
    },
    B {
        @Override
        public Rotation rotation(final Rotation facing, final Position at) {
            return facing;
        }

        @Override
        public Position position(final Rotation facing, final Position at) {
            return at.back(facing);
        }
    };

    public abstract Rotation rotation(
            final Rotation facing,
            final Position at);

    public abstract Position position(
            final Rotation facing,
            final Position at);
}
