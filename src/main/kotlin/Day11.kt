import Day11.FuelCell.Coordinate

class Day11 {
    fun part1Answer(gridSerialNumber: Int): Coordinate {
        val fuelCellGrid = FuelCellGrid(gridSerialNumber, 3)
        return fuelCellGrid.getSquareWithMaximumTotalPower(3).coordinate
    }

    fun part2Answer(gridSerialNumber: Int): SquareIdentifier {
        val fuelCellGrid = FuelCellGrid(gridSerialNumber, 20)
        return fuelCellGrid.getSquareWithMaximumTotalPower()
    }

    data class SquareIdentifier(val coordinate: Coordinate, val squareSize: Int, val totalPower: Int)

    data class FuelCellGrid(val gridSerialNumber: Int, val maxGridSize: Int = 300) {
        private val coordinateToFuelCellPowerLevel: MutableMap<Coordinate, Int> = mutableMapOf()
        val coordinateAndSquareSizeToTotalPowerLevel: MutableMap<SquareSizeAndCoordinate, Int> = mutableMapOf()

        init {
            for (y in 1..300) {
                for (x in 1..300) {
                    val fuelCell = FuelCell(Coordinate(x, y), gridSerialNumber)
                    coordinateToFuelCellPowerLevel[fuelCell.coordinate] = fuelCell.powerLevel
                }
            }
            for (squareSize in 1..maxGridSize) {
                for (y in 1..300 - squareSize + 1) {
                    for (x in 1..300 - squareSize + 1) {
                        val squareSizeAndCoordinate = SquareSizeAndCoordinate(Coordinate(x, y), squareSize)
                        val totalPowerOfSquare = getTotalPowerOfSquare(squareSizeAndCoordinate.coordinate, squareSize)
                        coordinateAndSquareSizeToTotalPowerLevel[squareSizeAndCoordinate] = totalPowerOfSquare
                    }
                }
            }
        }

        data class SquareSizeAndCoordinate(val coordinate: Coordinate, val squareSize: Int)


        fun getSquareWithMaximumTotalPower(squareSize: Int? = null): SquareIdentifier {
            val squareSizeAndCoordinate = coordinateAndSquareSizeToTotalPowerLevel
                    .filterKeys { squareSize == null || it.squareSize == squareSize }
                    .maxBy { it.value }!!
                    .key
            return SquareIdentifier(squareSizeAndCoordinate.coordinate,
                    squareSizeAndCoordinate.squareSize,
                    coordinateAndSquareSizeToTotalPowerLevel[squareSizeAndCoordinate]!!)
        }


        fun getTotalPowerOfSquare(topLeftCorner: Coordinate, squareSize: Int): Int {
            if (squareSize == 1) {
                return coordinateToFuelCellPowerLevel[topLeftCorner]!!
            } else {
                /**
                 * Calculate total power of given square by taking the square one size less, plus the left and top edges
                 */
                val coordinateForSmallerSquare = Coordinate(topLeftCorner.x + 1, topLeftCorner.y + 1)
                val squareSizeAndCoordinateOfSmallerSquare = SquareSizeAndCoordinate(coordinateForSmallerSquare, squareSize - 1)
                var totalSumOfPowerLevels = coordinateAndSquareSizeToTotalPowerLevel[squareSizeAndCoordinateOfSmallerSquare]!!
                for (x in topLeftCorner.x until topLeftCorner.x + squareSize) {
                    totalSumOfPowerLevels += coordinateToFuelCellPowerLevel[Coordinate(x, topLeftCorner.y)]!!
                }
                for (y in topLeftCorner.y + 1 until topLeftCorner.y + squareSize) { // Excluding the x,y
                    totalSumOfPowerLevels += coordinateToFuelCellPowerLevel[Coordinate(topLeftCorner.x, y)]!!
                }
                return totalSumOfPowerLevels
            }
        }

    }

    data class FuelCell(val coordinate: Coordinate, val gridSerialNumber: Int) {

        data class Coordinate(val x: Int, val y: Int)

        val powerLevel
            get() : Int {
                val rackId = coordinate.x + 10
                var powerLevelBaseLine = rackId * coordinate.y
                powerLevelBaseLine += gridSerialNumber
                powerLevelBaseLine *= rackId
                val hundredsDigit = (powerLevelBaseLine / 100) % 10
                return hundredsDigit - 5
            }
    }
}
