import java.io.File

class Day07 {
    fun part1Answer(): String {
        val edges = File(ClassLoader.getSystemResource("day07_input.txt").file)
                .useLines { it.toList() }
                .map { parseStepDependencyString(it) }
                .toMutableSet()
        val directedGraph = DirectedGraph(edges)
        val stepExecuteOrder: MutableList<Char> = mutableListOf()
        for (i in 0..directedGraph.nodes.size) {
            for (node in directedGraph.nodes.sorted()) {
                if (directedGraph.countIncomingEdges(node) == 0) {
                    stepExecuteOrder.add(node)
                    directedGraph.removeNode(node)
                    break
                }
            }
        }
        return stepExecuteOrder.joinToString(separator = "")
    }

    // Step A must be finished before step D can begin.
    fun parseStepDependencyString(inputString: String): Edge {
        val regex = """Step (.) must be finished before step (.) can begin.""".toRegex()
        val matchResult = regex.find(inputString)
        val (step, nextStep) = matchResult!!.destructured
        return Edge(step[0], nextStep[0])
    }

    fun part2Answer(numberOfWorkers: Int, baseDuration: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

data class DirectedGraph(val edges: MutableSet<Edge> = mutableSetOf()) {
    val nodes: MutableSet<Char> = mutableSetOf()

    init {
        val fromNodes = edges.map { it.from }.toSet()
        val toNodes = edges.map { it.to }.toSet()
        nodes.addAll(fromNodes.plus(toNodes))
    }

    fun countIncomingEdges(node: Char) = edges.filter { it.to == node }.size
    fun removeNode(node: Char) {
        require(countIncomingEdges(node) == 0) { "Cannot remove node ${node} because edge is pointing to it" }
        edges.removeIf { it.from == node }
        nodes.remove(node)
    }
}

data class Edge(val from: Char, val to: Char)



