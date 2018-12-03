data class SquarePosition(val position: Pair<Int, Int>)

data class SquareClaim(val claimIds: MutableList<Int> = mutableListOf()) {
    val count get() = claimIds.size
}
