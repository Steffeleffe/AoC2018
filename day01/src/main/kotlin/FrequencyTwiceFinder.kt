class FrequencyTwiceFinder {
    fun find(list : List<Int>) : Int {
        var sumSoFar = 0
        val uniqueSums : MutableSet<Int> = mutableSetOf(0)
        while (true) {
            for (number : Int in list) {
                sumSoFar += number
                if (!uniqueSums.add(sumSoFar)) {
                    return sumSoFar
                }
            }
        }
    }
}