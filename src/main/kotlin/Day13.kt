import java.io.File

fun main(args: Array<String>) {
    println("Day13")
    println("Part1: ${Day13().part1Answer()}")
    println("Part2: ${Day13().part2Answer()}")
}

class Day13 {
    fun part1Answer(): Coordinate {
        val trackAndCartSystem = InputParser().readInput()
        return trackAndCartSystem.moveCartsUntilFirstCollision()
    }

    fun part2Answer(): Coordinate {
        val trackAndCartSystem = InputParser().readInput()
        return trackAndCartSystem.moveCartsUntilLastCollision()
    }

    data class TrackAndCartSystem(val sections: Set<TrackSection>, val carts: MutableSet<Cart>) {

        private val coordinateToTrackSectionMap: Map<Coordinate, TrackSection> = sections.map { it.coordinate to it }.toMap()

        fun moveCartsUntilFirstCollision(): Coordinate {
            while (true) {
                for (cart in getCartSequenceForMoving()) {
                    moveCart(cart)
                    if (isSameCoordinateOccupiedByMoreThanOneCart().isNotEmpty()) {
                        return isSameCoordinateOccupiedByMoreThanOneCart().first()
                    }
                }
            }
        }

        /**
         * Removes colliding carts, and returns the coordinates for the final cart alive
         */
        fun moveCartsUntilLastCollision(): Day13.Coordinate {
            while (true) {
                for (cart in getCartSequenceForMoving()) {
                    moveCart(cart)
                    if (isSameCoordinateOccupiedByMoreThanOneCart().isNotEmpty()) {
                        val collisionCoordinate = isSameCoordinateOccupiedByMoreThanOneCart().first()
                        carts.removeIf { it.getCurrentPosition() == collisionCoordinate }
                    }
                }
                if (carts.size == 1) {
                    return carts.first().getCurrentPosition()
                }
            }
        }

        private fun getCartSequenceForMoving() = carts.sortedBy { it.getCurrentPosition().toString() }

        private fun moveCart(cart: Cart) {
            cart.moveInCurrentDirection()
            val trackSectionTypeAtNewCartPosition = coordinateToTrackSectionMap[cart.getCurrentPosition()]!!.type
            when (trackSectionTypeAtNewCartPosition) {
                TrackSection.TrackType.STRAIGHT_HORIZONTAL, TrackSection.TrackType.STRAIGHT_VERTICAL -> Unit // maintain cart direction
                TrackSection.TrackType.CURVE_BACKWARD_SLASH, TrackSection.TrackType.CURVE_FORWARD_SLASH -> changeDirectionOnCurve(trackSectionTypeAtNewCartPosition, cart)
                TrackSection.TrackType.INTERSECTION -> cart.handleIntersection()
            }
        }

        private fun changeDirectionOnCurve(type: TrackSection.TrackType, cart: Cart) {
            when (true) {
                cart.getCurrentDirection() == Cart.CartDirection.NORTH && type == TrackSection.TrackType.CURVE_FORWARD_SLASH -> cart.turnRight()
                cart.getCurrentDirection() == Cart.CartDirection.SOUTH && type == TrackSection.TrackType.CURVE_FORWARD_SLASH -> cart.turnRight()
                cart.getCurrentDirection() == Cart.CartDirection.WEST && type == TrackSection.TrackType.CURVE_FORWARD_SLASH -> cart.turnLeft()
                cart.getCurrentDirection() == Cart.CartDirection.EAST && type == TrackSection.TrackType.CURVE_FORWARD_SLASH -> cart.turnLeft()
                cart.getCurrentDirection() == Cart.CartDirection.NORTH && type == TrackSection.TrackType.CURVE_BACKWARD_SLASH -> cart.turnLeft()
                cart.getCurrentDirection() == Cart.CartDirection.SOUTH && type == TrackSection.TrackType.CURVE_BACKWARD_SLASH -> cart.turnLeft()
                cart.getCurrentDirection() == Cart.CartDirection.WEST && type == TrackSection.TrackType.CURVE_BACKWARD_SLASH -> cart.turnRight()
                cart.getCurrentDirection() == Cart.CartDirection.EAST && type == TrackSection.TrackType.CURVE_BACKWARD_SLASH -> cart.turnRight()
                else -> throw IllegalArgumentException()
            }
        }

        private fun isSameCoordinateOccupiedByMoreThanOneCart() =
                carts.groupingBy { it.getCurrentPosition() }.eachCount().filter { it.value > 1 }.keys

    }

    data class Coordinate(var x: Int, var y: Int)

    data class TrackSection(val coordinate: Coordinate, val type: TrackType) {
        enum class TrackType { STRAIGHT_VERTICAL, STRAIGHT_HORIZONTAL, CURVE_FORWARD_SLASH, CURVE_BACKWARD_SLASH, INTERSECTION }
    }

    data class Cart(private val coordinate: Coordinate, private var direction: CartDirection) {
        fun getCurrentDirection() = direction
        fun getCurrentPosition() = coordinate
        var intersectionChoice = 0
        fun handleIntersection() {
            when (intersectionChoice++ % 3) {
                0 -> turnLeft()
                1 -> Unit // maintain cart direction
                2 -> turnRight()
            }
        }

        fun moveInCurrentDirection() {
            when (direction) {
                CartDirection.NORTH -> coordinate.y--
                CartDirection.SOUTH -> coordinate.y++
                CartDirection.WEST -> coordinate.x--
                CartDirection.EAST -> coordinate.x++
            }
        }

        fun turnRight() {
            direction = when (direction) {
                CartDirection.NORTH -> CartDirection.EAST
                CartDirection.SOUTH -> CartDirection.WEST
                CartDirection.WEST -> CartDirection.NORTH
                CartDirection.EAST -> CartDirection.SOUTH
            }
        }

        fun turnLeft() {
            direction = when (direction) {
                CartDirection.NORTH -> CartDirection.WEST
                CartDirection.SOUTH -> CartDirection.EAST
                CartDirection.WEST -> CartDirection.SOUTH
                CartDirection.EAST -> CartDirection.NORTH
            }
        }

        enum class CartDirection {
            NORTH, SOUTH, WEST, EAST
        }
    }

    inner class InputParser {

        fun readInput(): TrackAndCartSystem {
            val rawInput = File(ClassLoader.getSystemResource("day13_input.txt").file).useLines { it.toList() }
            val cartsFromInput: MutableSet<Cart> = mutableSetOf()
            val trackSectionsFromInput: MutableSet<TrackSection> = mutableSetOf()
            for (y in 0 until rawInput.size) {
                val row = rawInput[y]
                for (x in 0 until row.length) {
                    val char = row[x]
                    if (isCartSymbol(char)) {
                        cartsFromInput.add(Cart(Coordinate(x, y), cartDirectionFromChar(char)))
                    }
                    if (char != ' ') {
                        trackSectionsFromInput.add(TrackSection(Coordinate(x, y), trackTypeFromChar(char)))
                    }
                }
            }
            return TrackAndCartSystem(trackSectionsFromInput, cartsFromInput)
        }

        private fun isCartSymbol(it: Char) = it == '<' || it == '>' || it == '^' || it == 'v'

        private fun cartDirectionFromChar(type: Char) = when (type) {
            '^' -> Cart.CartDirection.NORTH
            'v' -> Cart.CartDirection.SOUTH
            '<' -> Cart.CartDirection.WEST
            '>' -> Cart.CartDirection.EAST
            else -> throw IllegalArgumentException()
        }

        private fun trackTypeFromChar(type: Char) = when (type) {
            '|', '^', 'v' -> TrackSection.TrackType.STRAIGHT_VERTICAL
            '-', '<', '>' -> TrackSection.TrackType.STRAIGHT_HORIZONTAL
            '/' -> TrackSection.TrackType.CURVE_FORWARD_SLASH
            '\\' -> TrackSection.TrackType.CURVE_BACKWARD_SLASH
            '+' -> TrackSection.TrackType.INTERSECTION
            else -> throw IllegalArgumentException()
        }
    }

}
