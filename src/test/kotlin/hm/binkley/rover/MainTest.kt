package hm.binkley.rover

import com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemErr
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MainTest {
    @Test
    fun `should steer C-style`() = CStyle::main.assertInAndOut()

    @Test
    fun `should steer math-style`() = Mathy::main.assertInAndOut()

    companion object {
        private val inLines = lines("/input")
        private val expectedOutLines = lines("/output")

        private fun ((Array<out String>) -> Unit).assertInAndOut() =
            tapSystemOutNormalized {
                assertNothingWrittenToSystemErr {
                    withTextFromSystemIn(*inLines.toTypedArray()).execute {
                        this(emptyArray())
                    }
                }
            }.realLines() shouldBe expectedOutLines

        private fun lines(resourcePath: String) =
            requireNotNull(this::class.java.getResource(resourcePath))
                .readText()
                .realLines()
    }
}

// Ignore unreal "line" after terminal newline
private fun String.realLines() = lines().dropLast(1)
