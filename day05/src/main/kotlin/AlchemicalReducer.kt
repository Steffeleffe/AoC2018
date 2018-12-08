import java.util.*

class AlchemicalReducer {
    fun reduce(input: String): String {
        val stack = Stack<Char>()
        for (c in input.toCharArray()) {
            if (!stack.empty() && stack.peek().isOppositeCase(c)) {
                stack.pop()
            } else {
                stack.push(c)
            }
        }
        return stack.joinToString(separator = "")
    }

    fun improveReduce(input: String) =
            ('a'..'z')
                    .map { removeUnitFromPolymer(input, it) }
                    .map { reduce(it) }
                    .minBy { it.length } ?: ""

    fun removeUnitFromPolymer(polymer: String, unit: Char) =
            polymer.replace(unit.toString(), "", true)

}


private fun Char.isOppositeCase(c: Char) =
        !this.equals(c) && (this.toLowerCase().equals(c.toLowerCase()))

