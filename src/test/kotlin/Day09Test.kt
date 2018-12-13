import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day09Test {
    val marbleCircle = Day09.MarbleCircle()


    @Test
    fun testPart1() {
        assertThat(Day09().solve(9, 25)).isEqualTo(32)
        assertThat(Day09().solve(10, 1618)).isEqualTo(8317)
        assertThat(Day09().solve(13, 7999)).isEqualTo(146373)
        assertThat(Day09().solve(17, 1104)).isEqualTo(2764)
        assertThat(Day09().solve(21, 6111)).isEqualTo(54718)
        assertThat(Day09().solve(30, 5807)).isEqualTo(37305)
    }

    @Nested
    inner class TestRounds {
        @Test
        fun `round 1`() {
            marbleCircle.handleMarble(1)
            assertThat(marbleCircle.marbles).containsExactly(0, 1)
        }

        @Test
        fun `round 2`() {
            (1..2).forEach { marbleCircle.handleMarble(it) }
            assertThat(marbleCircle.marbles).containsExactly(0, 2, 1)
        }

        @Test
        fun `round 3`() {
            (1..3).forEach { marbleCircle.handleMarble(it) }
            assertThat(marbleCircle.marbles).containsExactly(0, 2, 1, 3)
        }

        @Test
        fun `round 4`() {
            (1..4).forEach { marbleCircle.handleMarble(it) }
            assertThat(marbleCircle.marbles).containsExactly(0, 4, 2, 1, 3)
        }

        @Test
        fun `round 8`() {
            (1..8).forEach { marbleCircle.handleMarble(it) }
            assertThat(marbleCircle.marbles).containsExactly(0, 8, 4, 2, 5, 1, 6, 3, 7)
        }

        @Test
        fun `round 22`() {
            (1..22).forEach { marbleCircle.handleMarble(it) }
            assertThat(marbleCircle.marbles).containsExactly(0, 16, 8, 17, 4, 18, 9, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15)
            assertThat(marbleCircle.currentMarbleId).isEqualTo(22)
        }

        @Test
        fun `round 23`() {
            (1..23).forEach { marbleCircle.handleMarble(it) }
            assertThat(marbleCircle.marbles).containsExactly(0, 16, 8, 17, 4, 18, 19, 2, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15)
            assertThat(marbleCircle.currentMarbleId).isEqualTo(19)
        }

        @Test
        fun `round 24`() {
            (1..24).forEach { marbleCircle.handleMarble(it) }
            assertThat(marbleCircle.marbles).containsExactly(0, 16, 8, 17, 4, 18, 19, 2, 24, 20, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15)
            assertThat(marbleCircle.currentMarbleId).isEqualTo(24)
        }

    }

}