import Day10.Light
import Day10.Light.Position
import Day10.Light.Velocity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day10Test {
    val readLightsFromInput = Day10().readLightsFromInput()

    @Test
    fun answerPart1() {
        assertThat(Day10().part1Answer()[0]).isEqualTo("#...#..###")
        assertThat(Day10().part1Answer()[1]).isEqualTo("#...#...#.")
        assertThat(Day10().part1Answer()[2]).isEqualTo("#...#...#.")
        assertThat(Day10().part1Answer()[3]).isEqualTo("#####...#.")
        assertThat(Day10().part1Answer()[4]).isEqualTo("#...#...#.")
        assertThat(Day10().part1Answer()[5]).isEqualTo("#...#...#.")
        assertThat(Day10().part1Answer()[6]).isEqualTo("#...#...#.")
        assertThat(Day10().part1Answer()[7]).isEqualTo("#...#..###")
    }

    @Test
    fun answerPart2() {
        assertThat(Day10().part2Answer()).isEqualTo(3)
    }

    @Test
    fun `parse light string with double digits and negative numbers`() {
        val expected = Light(Position(-3, 11), Velocity(1, -2))
        assertThat(Day10().parseLightString("position=<-3, 11> velocity=< 1, -2>")).isEqualTo(expected)
    }

    @Nested
    inner class LightPositionAtTime {
        val light = Light(Position(-3, 11), Velocity(1, -2))

        @Test
        fun `position at time 0`() {
            assertThat(light.getPositionAtTime(0)).isEqualTo(Position(-3, 11))
        }

        @Test
        fun `position at time 1`() {
            assertThat(light.getPositionAtTime(1)).isEqualTo(Position(-2, 9))
        }

        @Test
        fun `position at time 10`() {
            assertThat(light.getPositionAtTime(10)).isEqualTo(Position(7, -9))
        }

    }

    @Nested
    inner class PrintableSkyChart {
        @Test
        fun `second 0`() {
            val printableSkyChart = readLightsFromInput.printableSkyChart()
            assertThat(printableSkyChart[0]).isEqualTo("........#.............")
        }

        @Test
        fun `second 1`() {
            val printableSkyChart = readLightsFromInput.printableSkyChart(1)
            assertThat(printableSkyChart[0]).isEqualTo("........#....#....")
        }

        @Test
        fun `second 3`() {
            val printableSkyChart = readLightsFromInput.printableSkyChart(3)
            assertThat(printableSkyChart[0]).isEqualTo("#...#..###")
        }


    }

    @Nested
    inner class TotalAreaSkyDimension {

        @Test
        fun `around origin`() {
            val light1 = Light(Position(5, 5), Velocity(0, 0))
            val light2 = Light(Position(-4, -4), Velocity(0, 0))
            val skyLights = Day10.SkyLights(listOf(light1, light2))
            assertThat(skyLights.skyDimension().totalArea).isEqualTo(100)
        }

        @Test
        fun `all negative origin`() {
            val light1 = Light(Position(-14, -14), Velocity(0, 0))
            val light2 = Light(Position(-5, -5), Velocity(0, 0))
            val skyLights = Day10.SkyLights(listOf(light1, light2))
            assertThat(skyLights.skyDimension().totalArea).isEqualTo(100)
        }

    }
}

