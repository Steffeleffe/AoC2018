data class FabricClaim(val id: Int,
                       val leftMargin: Int,
                       val topMargin: Int,
                       val width: Int,
                       val height: Int) {

    val rightMargin get() = leftMargin + width

    val bottomMargin get() = topMargin + height
}
