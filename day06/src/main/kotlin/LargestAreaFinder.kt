class LargestAreaFinder {
    fun findLargestArea(inputCoordinates: List<Coordinate>): Int {

        val coordinateMap: CoordinateMap = calculateCoordinateMap(inputCoordinates)
        val findInfiniteAreaCoordinates = findInfiniteAreaCoordinates(coordinateMap)

        val filterKeys = coordinateMap.coordinateToSummaryMap
                .filterValues { !findInfiniteAreaCoordinates.contains(it.coordinate) }
        return filterKeys
                .values
                .groupingBy { it.coordinate }
                .eachCount()
                .values
                .max()!!
    }

    fun calculateCoordinateMap(inputCoordinates: List<Coordinate>): CoordinateMap {
        val coordinateToNearestInputCoordinate: MutableMap<Coordinate, Summary> = mutableMapOf()
        val (topLeft, bottomRight) = findAreaSpannedByInput(inputCoordinates)
        for (x in topLeft.x..bottomRight.x) {
            for (y in topLeft.y..bottomRight.y) {
                val coordinateIterated = Coordinate(x, y)
                coordinateToNearestInputCoordinate.put(coordinateIterated,
                        findNearestInputCoordinate(coordinateIterated, inputCoordinates))
            }
        }
        return CoordinateMap(coordinateToNearestInputCoordinate, topLeft, bottomRight)
    }

    fun findNearestInputCoordinate(from: Coordinate, inputCoordinates: List<Coordinate>): Summary {
        val shortestDistance1 = inputCoordinates.minBy { it.manhattenDistance(from) }
        val shortestDistance2 = inputCoordinates.asReversed().minBy { it.manhattenDistance(from) }
        val shortest =
                if (shortestDistance1 == shortestDistance2) {
                    shortestDistance1!!
                } else {
                    Coordinate(-1, -1)
                }
        val totalDistance = inputCoordinates.map { it.manhattenDistance(from) }.sum()
        return Summary(shortest, totalDistance)
    }

    /**
     * Find the coordinates that are closest to the edges of the area.
     */
    fun findInfiniteAreaCoordinates(coordinateMap: CoordinateMap): Set<Coordinate> {

        return coordinateMap.coordinateToSummaryMap.filterKeys {
            it.x == coordinateMap.bottomRight.x ||
                    it.x == coordinateMap.topLeft.x || it.y == coordinateMap.bottomRight.y || it.y == coordinateMap.topLeft.y
        }
                .values
                .map { it.coordinate }
                .toSet()
    }


    fun findBottomRightOfAreaSpannedByInput(inputCoordinates: List<Coordinate>) =
            Coordinate(inputCoordinates.map { it.x }.max()!!, inputCoordinates.map { it.y }.max()!!)

    fun findTopLeftAreaSpannedByInput(inputCoordinates: List<Coordinate>) =
            Coordinate(inputCoordinates.map { it.x }.min()!!, inputCoordinates.map { it.y }.min()!!)


    fun findAreaSpannedByInput(inputCoordinates: List<Coordinate>) =
            Pair(findTopLeftAreaSpannedByInput(inputCoordinates), findBottomRightOfAreaSpannedByInput(inputCoordinates))

    fun findCoordinatesWithinTotalDistance(inputCoordinates: List<Coordinate>, i: Int) =
            calculateCoordinateMap(inputCoordinates)
                    .coordinateToSummaryMap
                    .filterValues { it.totalDistance < i }
                    .size


}