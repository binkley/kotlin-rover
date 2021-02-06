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
    private val inLines = lines("/input")
    private val expectedOutLines = lines("/output")

    @Test
    fun `should steer C-style`() {
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
        val out = tapSystemOutNormalized {
            assertNothingWrittenToSystemErr {
                withTextFromSystemIn(*inLines).execute {
                    Mathy.main()
                }
            }
        }

        newline.split(out) shouldBe expectedOutLines
    }

    private fun lines(resourcePath: String) = BufferedReader(
        InputStreamReader(javaClass.getResourceAsStream(resourcePath)))
        .lines()
        .toArray { size -> Array(size) { "" } }
}
