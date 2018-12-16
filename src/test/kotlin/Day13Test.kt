import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day13Test {

    val trackAndCartSystem = Day13().InputParser().readInput()

    @Test
    fun solvePart1() {
        assertThat(Day13().part1Answer()).isEqualTo(Day13.Coordinate(7, 3))
    }

    @Nested
    inner class ReadInput {
        @Test
        fun `check carts`() {
            assertThat(trackAndCartSystem.carts.size).isEqualTo(2)
            assertThat(trackAndCartSystem.carts.any { it.getCurrentPosition() == Day13.Coordinate(0, 0) }).isFalse()
            assertThat(trackAndCartSystem.carts.any { it.getCurrentPosition() == Day13.Coordinate(9, 3) }).isTrue()
        }

        @Test
        fun `check track sections`() {
            assertThat(trackAndCartSystem.sections.any { it.coordinate == Day13.Coordinate(0, 0) }).isTrue()
            assertThat(trackAndCartSystem.sections.any { it.coordinate == Day13.Coordinate(7, 1) }).isTrue()
            assertThat(trackAndCartSystem.sections.any { it.coordinate == Day13.Coordinate(1, 7) }).isFalse()
        }
    }


}