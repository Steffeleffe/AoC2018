import java.io.File

fun main(args: Array<String>) {
    val inputCoordinates: List<Coordinate> = File(ClassLoader.getSystemResource("input.txt").file)
            .useLines { it.toList() }
            .map { toCoordinate(it) }
            .toList()
    println("Part1: ${LargestAreaFinder().findLargestArea(inputCoordinates)}")
    println("Part2: ${LargestAreaFinder().findCoordinatesWithinTotalDistance(inputCoordinates, 10000)}")
}

fun toCoordinate(it: String): Coordinate {
    val (x, y) = it.split(", ")
    return Coordinate(x.toInt(), y.toInt())
}
