class FabricSquareCounter {

    fun countSquaresWithDuplicateClaim(inputClaims: List<FabricClaim>): Int =
            findClaimsOnEachSquarePosition(inputClaims).filterValues { c -> c.count > 1 }.count()

    fun findFabricClaimWhereAllSquaresAreClaimedByNoOneElse(inputClaims: List<FabricClaim>): Set<Int> {
        val allClaimIds = inputClaims.map { c -> c.id }.toSet()
        val claimIdsWithClaimThatIsShared = findClaimsOnEachSquarePosition(inputClaims)
                .filterValues { c -> c.count > 1 }
                .flatMap { it.value.claimIds }
                .toSet()
        return allClaimIds.minus(claimIdsWithClaimThatIsShared)
    }

    fun findClaimsOnEachSquarePosition(inputClaims: List<FabricClaim>): MutableMap<SquarePosition, SquareClaim> {
        val claimCountOnSquarePosition: MutableMap<SquarePosition, SquareClaim> = mutableMapOf()
        for (claim in inputClaims) {
            for (position in getSquaresClaimed(claim)) {
                claimCountOnSquarePosition.getOrPut(position) { SquareClaim() }.claimIds.add(claim.id)
            }
        }
        return claimCountOnSquarePosition
    }

    fun getSquaresClaimed(claim: FabricClaim): List<SquarePosition> {
        val listOfClaimedSquares = mutableListOf<SquarePosition>()
        for (x in claim.leftMargin.until(claim.rightMargin)) {
            for (y in claim.topMargin.until(claim.bottomMargin)) {
                listOfClaimedSquares.add(SquarePosition(Pair(x, y)))
            }
        }
        return listOfClaimedSquares
    }

}
