package hm.binkley.rover.mathy

import hm.binkley.rover.mathy.Rotation.E
import hm.binkley.rover.mathy.Rotation.N
import hm.binkley.rover.mathy.Rotation.S
import hm.binkley.rover.mathy.Rotation.W
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PositionTest {
    @Test
    fun `should stay where I am`() {
        at(0, 0).forward(N).back(N) shouldBe at(0, 0)
    }

    @Test
    fun `should move North`() {
        at(0, 0).forward(N) shouldBe at(0, 1)
        at(0, 0).back(S) shouldBe at(0, 1)
    }

    @Test
    fun `should move East`() {
        at(0, 0).forward(E) shouldBe at(1, 0)
        at(0, 0).back(W) shouldBe at(1, 0)
    }

    @Test
    fun `should move South`() {
        at(0, 0).forward(S) shouldBe at(0, -1)
        at(0, 0).back(N) shouldBe at(0, -1)
    }

    @Test
    fun `should move West`() {
        at(0, 0).forward(W) shouldBe at(-1, 0)
        at(0, 0).back(E) shouldBe at(-1, 0)
    }
}
