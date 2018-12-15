import Day11.*
import Day11.FuelCell.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `answerPart1 for serial 18`() {
        assertThat(Day11().part1Answer(18)).isEqualTo(Coordinate(33, 45))
    }

    @Test
    fun `answerPart1 for serial 42`() {
        assertThat(Day11().part1Answer(42)).isEqualTo(Coordinate(21, 61))
    }

    @Test
    fun `answerPart2 for serial 18`() {
        assertThat(Day11().part2Answer(18)).isEqualTo(SquareIdentifier(Coordinate(90, 269), 16, 113))
    }

    @Test
    fun `answerPart2 for serial 42`() {
        assertThat(Day11().part2Answer(42)).isEqualTo(SquareIdentifier(Coordinate(232, 251), 12, 119))
    }

    @Nested
    inner class FuelCellPowerLevel {

        @Test
        fun `negative power level`() {
            val fuelCell = FuelCell(Coordinate(122, 79), 57)
            assertThat(fuelCell.powerLevel).isEqualTo(-5)
        }

        @Test
        fun `zero power level`() {
            val fuelCell = FuelCell(Coordinate(217, 196), 39)
            assertThat(fuelCell.powerLevel).isEqualTo(0)
        }

        @Test
        fun `positive power level`() {
            val fuelCell = FuelCell(Coordinate(101, 153), 71)
            assertThat(fuelCell.powerLevel).isEqualTo(4)
        }
    }

    @Nested
    inner class SquareFuelCellPowerLevel {

        @Test
        fun `for grid serial 18`() {
            val fuelCellGrid = FuelCellGrid(18, 3)
            val squareSizeAndCoordinate = FuelCellGrid.SquareSizeAndCoordinate(Coordinate(33, 45), 3)
            assertThat(fuelCellGrid.coordinateAndSquareSizeToTotalPowerLevel[squareSizeAndCoordinate]).isEqualTo(29)
        }

        @Test
        fun `for grid serial 42`() {
            val fuelCellGrid = FuelCellGrid(42, 3)
            val squareSizeAndCoordinate = FuelCellGrid.SquareSizeAndCoordinate(Coordinate(21, 61), 3)
            assertThat(fuelCellGrid.coordinateAndSquareSizeToTotalPowerLevel[squareSizeAndCoordinate]).isEqualTo(30)
        }

        @Test
        fun `for grid serial 18 and square size 16`() {
            val fuelCellGrid = FuelCellGrid(18, 16)
            val squareSizeAndCoordinate = FuelCellGrid.SquareSizeAndCoordinate(Coordinate(90, 269), 16)
            assertThat(fuelCellGrid.coordinateAndSquareSizeToTotalPowerLevel[squareSizeAndCoordinate]).isEqualTo(113)
        }

        @Test
        fun `for grid serial 42 and square size 12`() {
            val fuelCellGrid = FuelCellGrid(42, 12)
            val squareSizeAndCoordinate = FuelCellGrid.SquareSizeAndCoordinate(Coordinate(232, 251), 12)
            assertThat(fuelCellGrid.coordinateAndSquareSizeToTotalPowerLevel[squareSizeAndCoordinate]).isEqualTo(119)
        }

    }

}