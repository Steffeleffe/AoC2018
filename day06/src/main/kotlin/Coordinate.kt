import kotlin.math.abs

data class Coordinate(val x: Int, val y: Int) {
    fun manhattenDistance(other: Coordinate) =
            abs(x - other.x) + abs(y - other.y)
}
