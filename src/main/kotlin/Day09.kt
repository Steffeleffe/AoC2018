import java.util.*

class Day09 {
    fun solve(players: Int, lastMarbleValue: Int): Long {

        val playerScore: MutableMap<Int, Long> = mutableMapOf()
        val marbleCircle = MarbleCircle()
        for (marble in 1..lastMarbleValue) {
            val playerId = (marble % players) + 1
            val score = marbleCircle.handleMarble(marble)
            playerScore.merge(playerId, score.toLong(), Long::plus)
        }

        return playerScore.values.max()!!
    }

    class MarbleCircle {
        val currentMarbleId get() = marblesDeque.peek()!!
        val marbles get() = getArrangedList()

        /**
         * Return list comparable to the order in the puzzle instructions
         */
        private fun getArrangedList(): List<Int> {
            val copy = LinkedList(marblesDeque)
            while (copy.last != 0) {
                copy.rotate(1)
            }
            return copy.toList().reversed()
        }

        private val marblesDeque: Deque<Int> = LinkedList<Int>().also { it.add(0) }

        fun handleMarble(marbleId: Int): Int =
                if (marbleId % 23 == 0) {
                    remove(marbleId)
                } else {
                    insert(marbleId)
                }

        private fun insert(marbleId: Int): Int {
            marblesDeque.rotate(1)
            marblesDeque.push(marbleId)
            return 0
        }

        private fun remove(marbleId: Int): Int {
            marblesDeque.rotate(-7)
            val score = marbleId + marblesDeque.pop()
            marblesDeque.rotate(1)
            return score
        }

        private fun Deque<Int>.rotate(n: Int): Unit =
                when {
                    n < 0 -> repeat(-n) {
                        addLast(removeFirst())
                    }
                    else -> repeat(n) {
                        addFirst(removeLast())
                    }
                }
    }
}




