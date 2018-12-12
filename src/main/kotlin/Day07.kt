import java.io.File

class Day07 {
    fun part1Answer(): String {
        val directedGraph = createDirectedGraphFromInput()
        val stepExecuteOrder: MutableList<Char> = mutableListOf()
        while (directedGraph.hasMoreWork()) {
            val node = directedGraph.getNextNodeReadyForWork()
            stepExecuteOrder.add(node.id)
            node.markAsComplete()
        }
        return stepExecuteOrder.joinToString(separator = "")
    }

    private fun createDirectedGraphFromInput(baseDuration: Int = 0): DirectedGraph {
        val edges = File(ClassLoader.getSystemResource("day07_input.txt").file)
                .useLines { it.toList() }
                .map { parseStepDependencyString(it) }
                .toMutableSet()
        return DirectedGraph(edges, baseDuration)
    }

    // Parse string like: "Step A must be finished before step D can begin."
    fun parseStepDependencyString(inputString: String): EdgeInput {
        val regex = """Step (.) must be finished before step (.) can begin.""".toRegex()
        val matchResult = regex.find(inputString)
        val (step, nextStep) = matchResult!!.destructured
        return EdgeInput(step[0], nextStep[0])
    }

    fun part2Answer(numberOfWorkers: Int, baseDuration: Int): Int {
        val directedGraph = createDirectedGraphFromInput(baseDuration)
        var secondCount = 0
        val workers: MutableList<Worker> = mutableListOf()
        for (i in 0 until numberOfWorkers) {
            workers.add(Worker(i))
        }
        while (directedGraph.hasMoreWork()) {
            for (node in directedGraph.getNodesReadyForWork()) {
                if (workers.none { it.workingOn == node }) {
                    workers.firstOrNull { it.workingOn == null }?.workingOn = node
                }
            }
            secondCount++
            workers.filter { it.workingOn != null }.forEach {
                it.workingOn!!.registerWork()
                if (it.workingOn!!.isWorkComplete) {
                    it.workingOn = null
                }
            }
        }
        return secondCount
    }

    data class EdgeInput(val from: Char, val to: Char)

    data class Worker(val id: Int, var workingOn: Node? = null)

    class Node(val id: Char, baseDuration: Int = 0) {
        val cost = baseDuration + (id - 'A' + 1)
        var remainingWork: Int = cost
        val isWorkComplete get() : Boolean = remainingWork == 0
        fun registerWork(seconds: Int = 1) {
            remainingWork -= seconds
        }

        fun markAsComplete() {
            remainingWork = 0
        }
    }

    class DirectedGraph(inputEdges: MutableSet<EdgeInput>, baseDuration: Int = 0) {
        val nodes: MutableSet<Node> = mutableSetOf()
        private val edges: MutableSet<Edge> = mutableSetOf()

        init {
            val nodesMap: MutableMap<Char, Node> = mutableMapOf()
            for (inputEdge in inputEdges) {
                val fromNode = nodesMap.getOrPut(inputEdge.from) { Node(inputEdge.from, baseDuration) }
                val toNode = nodesMap.getOrPut(inputEdge.to) { Node(inputEdge.to, baseDuration) }
                edges.add(Edge(fromNode, toNode))
            }
            nodes.addAll(nodesMap.values)
        }

        fun isNodeReadyForWork(node: Node) = edges.filter { it.to == node }
                .map { it.from }
                .all { it.isWorkComplete }

        fun getNextNodeReadyForWork() = getNodesReadyForWork()[0]

        fun getNodesReadyForWork() =
                nodes.sortedBy { it.id }
                        .filter { isNodeReadyForWork(it) && !it.isWorkComplete }

        fun hasMoreWork() = getNodesReadyForWork().isNotEmpty()


        data class Edge(val from: Node, val to: Node)


    }


}


