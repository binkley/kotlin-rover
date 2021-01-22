package hm.binkley.rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

/**
 * {@code CStyleMain} is a very "C"-like approach to the Rover problem.
 *
 * @author <a href="mailto:binkley@alumni.rice.edu">B. K. Oxley (binkley)</a>
 * @see <a href="http://arunrocks.com/mars-rover-in-python-and-haskell/">Mars
 * Rover in Python and Haskell</a>
 */
public final class CStyleMain {
    public static void main(final String... args)
            throws IOException {
        final var lines = new BufferedReader(new InputStreamReader(in))
                .lines()
                .collect(toList());

        if (0 == lines.size() % 2)
            throw new IOException("Malformed input");

        // TODO: Skip grid size - what to do with it?
        for (int i = 1, l = lines.size(); i < l; ++i) {
            final String co = lines.get(i);
            final String[] coords = co.split(" ", 3);
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            int d = "ENWS".indexOf(coords[2].charAt(0));

            final String mo = lines.get(++i);
            for (int j = 0, k = mo.length(); j < k; ++j) {
                switch (mo.charAt(j)) {
                case 'L':
                    d += 1;
                    break;
                case 'R':
                    d += 3;
                    break;
                case 'M':
                    switch (d % 4) {
                    case 0:
                        ++x;
                        break;
                    case 1:
                        ++y;
                        break;
                    case 2:
                        --x;
                        break;
                    case 3:
                        --y;
                        break;
                    }
                    break;
                }
            }
            out.printf("%d %d %c%n", x, y, "ENWS".charAt(d % 4));
        }
    }
}
