class BoxIdDiffer {

    fun findCommonPrototypeBoxIds(list : List<String>) : String {
        val relatedBoxes = identifyRelatedBoxes(list).toList()
        check(relatedBoxes.size == 2)
        return relatedBoxes[0].getCommonCharacters(relatedBoxes[1])
    }

    /**
     * Given list of ids, returns set of those box ids that differ only by a single character
     */
    fun identifyRelatedBoxes(list : List<String>) : Set<String> {
        val relatedBoxes : MutableSet<String> = mutableSetOf()
        for (i in list.indices) {
            for (j in 0 until i) {
                val boxI = list[i]
                val boxJ = list[j]
                if (boxI.almostTheSame(boxJ)) {
                    relatedBoxes.addAll(setOf(boxI, boxJ))
                }
            }
        }
        return relatedBoxes.toSet()
    }

}

fun String.getCommonCharacters(other: String): String {
    require(this.length == other.length)
    val commonCharacters : MutableList<Char> = mutableListOf()
    this.forEachIndexed { index, c -> if (c == other[index]) commonCharacters.add(c) }
    return String(commonCharacters.toCharArray())
}

/**
 * Returns true if the two Strings differ only by a single character.
 */
fun String.almostTheSame(other : String) :  Boolean {
    require(this.length == other.length)
    var distance = 0
    this.forEachIndexed { index, c -> if (c != other[index]) distance++ }
    return distance == 1
}