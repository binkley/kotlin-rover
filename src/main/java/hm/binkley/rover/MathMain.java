package hm.binkley.rover;

import hm.binkley.rover.mathy.Instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static hm.binkley.rover.mathy.Path.path;
import static hm.binkley.rover.util.PairIterable.pairsOf;
import static hm.binkley.rover.util.StringIterable.over;
import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

/**
 * {@code MathMain} is a more OOP-y approach to the Rover problem based on <a
 * href="https://en.wikipedia.org/wiki/Rotation_matrix">matrix form</a> of <a
 * href="https://en.wikipedia.org/wiki/Circle_group">the circle group</a>.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 * @see <a href="http://arunrocks.com/mars-rover-in-python-and-haskell/">Mars
 * Rover in Python and Haskell</a>
 */
public final class MathMain {
    public static void main(final String... args)
            throws IOException {
        final var lines = new BufferedReader(new InputStreamReader(in))
                .lines()
                .collect(toList());

        if (0 == lines.size() % 2)
            throw new IOException("Malformed input");

        // TODO: Skip grid size - what to do with it?
        for (final var p : pairsOf(lines.subList(1, lines.size()))) {
            var path = path(p.a);
            for (final String n : over(p.b))
                path = path.next(Instruction.valueOf(n));
            out.println(path);
        }
    }
}
