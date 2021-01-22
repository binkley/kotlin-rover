package hm.binkley.rover

import com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemErr
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern

private val newline = Pattern.compile("\\n")

internal class MainTest {
    @Test
    fun `should steer C-style`() {
        val inLines = lines("/input")
        val expectedOutLines = lines("/output")
        val out = tapSystemOutNormalized {
            assertNothingWrittenToSystemErr {
                withTextFromSystemIn(*inLines).execute {
                    CStyle.main()
                }
            }
        }

        newline.split(out) shouldBe expectedOutLines
    }

    @Test
    fun `should steer math-style`() {
        val inLines = lines("/input")
        val expectedOutLines = lines("/output")
        val out = tapSystemOutNormalized {
            assertNothingWrittenToSystemErr {
                withTextFromSystemIn(*inLines).execute {
                    Mathy.main()
                }
            }
        }

        newline.split(out) shouldBe expectedOutLines
    }

    private fun lines(resourcePath: String): Array<String> {
        return BufferedReader(
            InputStreamReader(javaClass.getResourceAsStream(resourcePath))
        )
            .lines()
            .toArray { size -> Array(size) { "" } }
    }
}
