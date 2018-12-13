import java.io.File

class Day08 {
    fun part1Answer(): Int {
        val rootNode = parseInput()
        return rootNode.metadataTotalSum()
    }

    fun part2Answer(): Int {
        val rootNode = parseInput()
        return rootNode.value
    }

    private fun parseInput(): Node {
        val inputString = File(ClassLoader.getSystemResource("day08_input.txt").file).readText()
        val input = inputString.split(' ').map { it.toInt() }.iterator()
        return parseNode(input)
    }

    fun parseNode(input: Iterator<Int>): Node {
        val numberOfChildNodes = input.next()
        val numberOfMetadataEntries = input.next()
        val childNodes: MutableList<Node> = mutableListOf()
        for (i in 0 until numberOfChildNodes) {
            childNodes.add(parseNode(input))
        }
        val metadataEntries: MutableList<Int> = mutableListOf()
        for (i in 0 until numberOfMetadataEntries) {
            metadataEntries.add(input.next())
        }

        return Node(childNodes, metadataEntries)
    }

    data class Node(val childNodes: List<Node> = listOf(),
                    val metadataEntries: List<Int> = listOf()) {
        fun metadataTotalSum(): Int {
            return metadataEntries.sum() + childNodes.sumBy { it.metadataTotalSum() }
        }

        val value: Int =
                if (childNodes.isEmpty()) {
                    metadataEntries.sum()
                } else {
                    metadataEntries.mapNotNull { childNodes.getOrNull(it - 1) }.map { it.value }.sum()
                }
    }


}

