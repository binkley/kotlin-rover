package hm.binkley.rover

import com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemErr
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MainTest {
    private val inLines = lines("/input")
    private val expectedOutLines = lines("/output")

    @Test
    fun `should steer C-style`() {
        val stdout = tapSystemOutNormalized {
            assertNothingWrittenToSystemErr {
                withTextFromSystemIn(*inLines.toTypedArray()).execute {
                    CStyle.main()
                }
            }
        }

        stdout.realLines() shouldBe expectedOutLines
    }

    @Test
    fun `should steer math-style`() {
        val out = tapSystemOutNormalized {
            assertNothingWrittenToSystemErr {
                withTextFromSystemIn(*inLines.toTypedArray()).execute {
                    Mathy.main()
                }
            }
        }

        out.realLines() shouldBe expectedOutLines
    }

    private fun lines(resourcePath: String) =
        requireNotNull(this::class.java.getResource(resourcePath))
            .readText()
            .realLines()
}

// Ignore unreal "line" after terminal newline
private fun String.realLines() = lines().dropLast(1)
