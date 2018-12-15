import java.io.File

class Day12 {
    fun part1Answer(): Long {
        val readPotInput = readPotInput()
        return readPotInput.getSumOfPotNumbersAtGeneration(20)
    }

    fun part2Answer(): Long {
        val readPotInput = readPotInput()
        return readPotInput.getSumOfPotNumbersAtGenerationExtrapolated(50000000000)
    }

    fun readPotInput(): PotsHandler {
        val rawInput = File(ClassLoader.getSystemResource("day12_input.txt").file)
                .useLines { it.toList() }
        val initialState = parseInitialState(rawInput[0].replace("initial state: ", ""))
        val spreadRules = parseRules(rawInput.takeLast(rawInput.size - 2))
        return PotsHandler(initialState, spreadRules)
    }

    private fun parseRules(rules: List<String>): SpreadRules {
        val rulesSet = rules.map { parseRule(it) }
                .filterNotNull()
                .toSet()
        return SpreadRules(rulesSet)
    }

    /**
     * Parse: ####. => #
     * Returns null if rule makes pot empty (ie. LLCRR => .)
     */
    fun parseRule(inputString: String): String? {
        val regex = """(.+) => (.)""".toRegex()
        val matchResult = regex.find(inputString)
        val (pattern, result) = matchResult!!.destructured
        return if (result == ".") {
            null
        } else {
            pattern
        }
    }

    /**
     * First index in state is 0.
     * initial state: #..#.#..##......###...###
     */
    fun parseInitialState(inputString: String): PotState {
        val setOfPotNumbersWithPlant = inputString.toCharArray().withIndex().filter { it.value == '#' }.map { it.index }.toSet()
        return PotState(setOfPotNumbersWithPlant)
    }


    data class PotState(val potsWithPlant: Set<Int>) {
        /**
         * Creates the LLCRR string for the pots with C at the given index.
         */
        fun getNoteForIndex(index: Int): String {
            return (index - 2..index + 2).map { getIndexCharForIndex(it) }.joinToString(separator = "")
        }

        private fun getIndexCharForIndex(index: Int): String {
            return if (potsWithPlant.contains(index)) "#" else "."
        }

        val sumOfPotNumbersThatContainPlant get() = potsWithPlant.sum().toLong()

    }

    data class SpreadRules(val spreadRulesCreatePlant: Set<String>) {
        fun hasRuleCreatingPlant(ruleNoteKey: String): Boolean {
            return spreadRulesCreatePlant.contains(ruleNoteKey)
        }

    }

    data class PotsHandler(val state: PotState, val spreadRules: SpreadRules) {
        fun getPotStateAtGeneration(generation: Long): PotState {
            var potStateAtGeneration = state
            for (gen in 0 until generation) {
                potStateAtGeneration = calculateNextGeneration(potStateAtGeneration)
            }
            return potStateAtGeneration
        }

        private fun calculateNextGeneration(potState: PotState): PotState {
            val firstPotNumber = potState.potsWithPlant.first()
            val lastPotNumber = potState.potsWithPlant.last()
            val nextGenerationPotNumbersWithPlant: MutableSet<Int> = mutableSetOf()
            for (index in firstPotNumber - 2..lastPotNumber + 2) {
                val ruleNoteKey = potState.getNoteForIndex(index)
                if (spreadRules.hasRuleCreatingPlant(ruleNoteKey)) {
                    nextGenerationPotNumbersWithPlant.add(index)
                }
            }
            return PotState(nextGenerationPotNumbersWithPlant)
        }

        fun getSumOfPotNumbersAtGenerationExtrapolated(generation: Long): Long {
            var lastSum = getSumOfPotNumbersAtGeneration(0)
            var lastDifference = Long.MIN_VALUE
            for (generationIndex in (100..generation step 100)) {
                val newSum = getSumOfPotNumbersAtGeneration(generationIndex)
                val newDifference = newSum - lastSum
                if (newDifference == lastDifference) {
                    val remainingGenerationLoops = (generation - generationIndex) / 100
                    return newSum + remainingGenerationLoops * newDifference
                }
                lastSum = newSum
                lastDifference = newDifference
            }
            return Long.MIN_VALUE
        }

        fun getSumOfPotNumbersAtGeneration(generation: Long): Long {
            return getPotStateAtGeneration(generation).sumOfPotNumbersThatContainPlant
        }

    }

}
