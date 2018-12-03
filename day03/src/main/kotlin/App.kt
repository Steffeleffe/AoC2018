import java.io.File

fun main(args: Array<String>) {
    val claimsIds: List<FabricClaim> = File(ClassLoader.getSystemResource("input.txt").file)
            .useLines { it.toList() }
            .map { it.toFabricClaim() }
    println("Part1: ${FabricSquareCounter().countSquaresWithDuplicateClaim(claimsIds)}")
    println("Part2: ${FabricSquareCounter().findFabricClaimWhereAllSquaresAreClaimedByNoOneElse(claimsIds)}")

}

/**
 * Claim format in input file:
 * #123 @ 3,2: 5x4
 */
fun String.toFabricClaim(): FabricClaim {
    val regex = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()
    val matchResult = regex.find(this)
    val (id, leftMargin, topMargin, width, height) = matchResult!!.destructured
    return FabricClaim(id.toInt(), leftMargin.toInt(), topMargin.toInt(), width.toInt(), height.toInt())
}
