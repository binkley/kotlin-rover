package hm.binkley.rover

import com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemErr
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MainTest {
    @Test
    fun `should steer C-style`() = CStyle::main.assertInAndOut()

    @Test
    fun `should steer math-style`() = Mathy::main.assertInAndOut()

    @Test
    fun `should do nothing on bad input C-style`() =
        CStyle::main.assertMissingBoundary()

    @Test
    fun `should do nothing on bad input math-style`() =
        Mathy::main.assertMissingBoundary()
}

private val goodInLines = lines("/input")
private val goodExpectedOutLines = lines("/output")
private val missingBoundaryInLines = lines("/missing-boundary")

private fun ((Array<out String>) -> Unit).assertInAndOut() =
    assertNothingWrittenToSystemErr {
        tapSystemOutNormalized {
            withTextFromSystemIn(*goodInLines.toTypedArray()).execute {
                this(emptyArray())
            }
        }.realLines() shouldBe goodExpectedOutLines
    }

private fun ((Array<out String>) -> Unit).assertMissingBoundary() =
    assertThrows<IllegalArgumentException> {
        assertNothingWrittenToSystemErr {
            tapSystemOutNormalized {
                withTextFromSystemIn(*missingBoundaryInLines.toTypedArray()).execute {
                    this(emptyArray())
                }
            }.realLines() shouldBe goodExpectedOutLines
        }
    }.message shouldBe "Malformed input"

// Ignore unreal "line" after terminal newline
private fun String.realLines() = lines().dropLast(1)

private fun lines(resourcePath: String) =
    requireNotNull(MainTest::class.java.getResource(resourcePath))
        .readText()
        .realLines()
