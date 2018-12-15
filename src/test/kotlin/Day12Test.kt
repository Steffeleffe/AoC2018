import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day12Test {

    val potsHandler = Day12().readPotInput()
    @Test
    fun solvePart1() {
        assertThat(Day12().part1Answer()).isEqualTo(325)
    }

    @Test
    fun testParseInitialState() {
        val input = "#..#.#..##......###...###"
        val expectedPotState = Day12.PotState(setOf(0, 3, 5, 8, 9, 16, 17, 18, 22, 23, 24))
        assertThat(Day12().parseInitialState(input)).isEqualTo(expectedPotState)
    }

    @Nested
    inner class ParseRules {
        @Test
        fun `rule with no plant in next generation`() {
            val inputRule = "..#.. => ."
            assertThat(Day12().parseRule(inputRule)).isNull()
        }

        @Test
        fun `rule with plant in next generation`() {
            val inputRule = ".##.# => #"
            assertThat(Day12().parseRule(inputRule)).isEqualTo(".##.#")
        }
    }

    @Nested
    inner class PotStateByGenerationForInput {
        @Test
        fun `generation 0`() {
            val expectedPotState = Day12.PotState(setOf(0, 3, 5, 8, 9, 16, 17, 18, 22, 23, 24))
            assertThat(potsHandler.getPotStateAtGeneration(0)).isEqualTo(expectedPotState)
        }

        @Test
        fun `generation 1`() {
            val expectedPotState = Day12.PotState(setOf(0, 4, 9, 15, 18, 21, 24))
            assertThat(potsHandler.getPotStateAtGeneration(1)).isEqualTo(expectedPotState)
        }

        @Test
        fun `generation 20`() {
            val expectedPotState = Day12.PotState(setOf(-2, 3, 4, 9, 10, 11, 12, 13, 17, 18, 19, 20, 21, 22, 23, 28, 30, 33, 34))
            assertThat(potsHandler.getPotStateAtGeneration(20)).isEqualTo(expectedPotState)
        }
    }

    @Test
    fun `get note for index`() {
        val input = "#..#.#..##......###...###"
        assertThat(Day12().parseInitialState(input).getNoteForIndex(0)).isEqualTo("..#..")
        assertThat(Day12().parseInitialState(input).getNoteForIndex(10)).isEqualTo("##...")
    }

    @Test
    fun `get sum extrapolated 10000 generations`() {
        val expectedSum = potsHandler.getSumOfPotNumbersAtGeneration(10000)
        assertThat(potsHandler.getSumOfPotNumbersAtGenerationExtrapolated(10000)).isEqualTo(expectedSum)
    }

}