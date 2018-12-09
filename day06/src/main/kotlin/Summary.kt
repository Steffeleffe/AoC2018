data class Summary(val coordinate: Coordinate,
                   val totalDistance: Int) {
    val x get() = coordinate.x
    val y get() = coordinate.y

}
