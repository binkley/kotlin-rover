package hm.binkley.rover

import com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemErr
import com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized
import com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

private typealias EntryPoint = (Array<out String>) -> Unit

internal class MainTest {
    @ParameterizedTest
    @MethodSource("assertions")
    fun `should work for C-style implementation`(assertion: EntryPoint.() -> Unit) {
        CStyle::main.assertion()
    }

    @ParameterizedTest
    @MethodSource("assertions")
    fun `should work for math style implementation`(assertion: EntryPoint.() -> Unit) {
        Mathy::main.assertion()
    }

    companion object {
        @JvmStatic
        fun assertions() = listOf(
            EntryPoint::assertInAndOut,
            EntryPoint::assertMissingBoundary,
            EntryPoint::assertMissingPosition,
            EntryPoint::assertBadStartingPosition,
            EntryPoint::assertStartingPastXBoundary,
            EntryPoint::assertStartingPastYBoundary,
            EntryPoint::assertMovingPastBoundary,
        )
    }
}

private fun EntryPoint.runInAndOutAndNoErr(
    inputLines: List<String>,
) = tapSystemOutNormalized {
    assertNothingWrittenToSystemErr {
        withTextFromSystemIn(*inputLines.toTypedArray()).execute {
            this(emptyArray())
        }
    }
}.realLines()

private fun EntryPoint.assertInAndOut() =
    runInAndOutAndNoErr(goodInLines) shouldBe goodExpectedOutLines

private fun EntryPoint.assertStaysInPlace() =
    runInAndOutAndNoErr(staysInPlaceInLines) shouldBe staysInPlaceOutLines

private fun EntryPoint.assertMissingBoundary() =
    assertMalformedInput(
        missingBoundaryInLines,
        "Line #1: Malformed input: 1 2 N"
    )

private fun EntryPoint.assertMissingPosition() =
    assertMalformedInput(
        missingPositionInLines,
        "Line #2: Malformed input: LMLMLMLMM"
    )

private fun EntryPoint.assertBadStartingPosition() =
    assertMalformedInput(
        badStartingPositionInLines,
        "Line #2: Malformed input: 1 N"
    )

private fun EntryPoint.assertStartingPastXBoundary() =
    assertMalformedInput(
        startingPastXBoundaryInLines,
        "Line #2: Malformed input: 6 2 N"
    )

private fun EntryPoint.assertStartingPastYBoundary() =
    assertMalformedInput(
        startingPastYBoundaryInLines,
        "Line #2: Malformed input: 1 6 N"
    )

private fun EntryPoint.assertMovingPastBoundary() =
    assertMalformedInput(
        movingPastBoundaryInLines,
        "Line #5: Malformed input: MMRMMRMRRM"
    )

private fun EntryPoint.assertMalformedInput(
    inputLines: List<String>,
    expectedExceptionMessage: String,
) {
    assertThrows<IllegalArgumentException> {
        runInAndOutAndNoErr(inputLines)
    }.message shouldBe expectedExceptionMessage
}

private val goodInLines = lines("/input")
private val goodExpectedOutLines = lines("/output")
private val staysInPlaceInLines = lines("/stays-in-place-input")
private val staysInPlaceOutLines = lines("/stays-in-place-output")
private val missingBoundaryInLines = lines("/missing-boundary")
private val missingPositionInLines = lines("/missing-position")
private val badStartingPositionInLines = lines("/bad-starting-position")
private val startingPastXBoundaryInLines = lines("/starting-past-x-boundary")
private val startingPastYBoundaryInLines = lines("/starting-past-y-boundary")
private val movingPastBoundaryInLines = lines("/moving-past-boundary")

// Ignore unreal "line" after terminal newline
// TODO: Check last char is terminating newline
private fun String.realLines() = lines().dropLast(1)

private fun lines(resourcePath: String) =
    requireNotNull(MainTest::class.java.getResource(resourcePath))
        .readText()
        .realLines()
