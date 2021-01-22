package hm.binkley.rover;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import static com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemErr;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static java.util.regex.Pattern.compile;
import static org.assertj.core.api.Assertions.assertThat;

final class MainTest {
    private static final Pattern newline = compile("\\n");

    @ParameterizedTest
    @EnumSource(Main.class)
    public void shouldSteer(final Main main)
            throws Exception {
        final String[] inLines = lines("/input");
        final String[] expectedOutLines = lines("/output");

        final var out = tapSystemOutNormalized(() ->
                assertNothingWrittenToSystemErr(() ->
                        withTextFromSystemIn(inLines)
                                .execute(main::run)));

        assertThat(newline.split(out)).isEqualTo(expectedOutLines);
    }

    private enum Main {
        C_STYLE() {
            @Override
            public void run(String... args) throws IOException {
                CStyleMain.main();
            }
        }, MATH() {
            @Override
            public void run(String... args) throws IOException {
                MathMain.main();
            }
        };

        public abstract void run(final String... args) throws IOException;
    }

    private String[] lines(final String resourcePath) {
        return new BufferedReader(
                new InputStreamReader(
                        getClass().getResourceAsStream(resourcePath)))
                .lines()
                .toArray(String[]::new);
    }
}
