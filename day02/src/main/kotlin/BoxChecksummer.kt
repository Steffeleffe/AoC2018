/**
 * Counting the number that have an ID containing exactly two of any letter and then separately counting those with
 * exactly three of any letter. You can multiply those two counts together to get a rudimentary checksum.
 */
class BoxChecksummer {
    /**
     * Calculate the checksum of the given box ids.
     */
    fun calculate(list: List<String>) =
         countBoxesWithLettersAppearingExactlyTwice(list) * countBoxesWithLettersAppearingExactlyThreeTimes(list)

    fun countBoxesWithLettersAppearingExactlyTwice(list: List<String>) =
        list.filter { it.hasLettersAppearingExactCount(2) }.count()

    fun countBoxesWithLettersAppearingExactlyThreeTimes(list: List<String>) =
        list.filter { it.hasLettersAppearingExactCount(3) }.count()

}

fun String.hasLettersAppearingExactCount(count : Int) =
        this.splitToSequence("").filterNot { it.equals("")}.groupingBy { it }.eachCount().any { it.value==count }
