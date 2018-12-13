import Day07.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day07Test {
    @Test
    fun testPart1Answer() {
        assertThat(Day07().part1Answer()).isEqualTo("CABDFE")
    }

    @Test
    fun testPart2Answer() {
        assertThat(Day07().part2Answer(2, 0)).isEqualTo(15)
    }

    @Test
    fun testParseStepDependencyString() {
        assertThat(Day07().parseStepDependencyString("Step A must be finished before step B can begin."))
                .isEqualTo(EdgeInput('A', 'B'))
    }

    @Test
    fun testNodesInGraph() {
        val input = mutableSetOf(EdgeInput('A', 'D'), EdgeInput('B', 'E'), EdgeInput('D', 'E'))
        assertThat(DirectedGraph(input).nodes.map { it.id }).containsExactlyInAnyOrder('A', 'B', 'D', 'E')
    }

    @Test
    fun testIsNodeReadyForWork() {
        val input = mutableSetOf(EdgeInput('A', 'D'), EdgeInput('B', 'E'), EdgeInput('D', 'E'))
        val directedGraph = DirectedGraph(input)
        val nodesMap = directedGraph.nodes.map { it.id to it }.toMap()
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['A']!!)).isEqualTo(true)
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['B']!!)).isEqualTo(true)
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['D']!!)).isEqualTo(false)
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['E']!!)).isEqualTo(false)
    }

    @Test
    fun testGetNextNodeReadyForWork() {
        val input = mutableSetOf(EdgeInput('A', 'D'), EdgeInput('B', 'E'), EdgeInput('D', 'E'))
        val directedGraph = DirectedGraph(input)
        val nodesMap = directedGraph.nodes.map { it.id to it }.toMap()
        assertThat(directedGraph.getNextNodeReadyForWork().id).isEqualTo('A')
        nodesMap.get('A')!!.markAsComplete()
        assertThat(directedGraph.getNextNodeReadyForWork().id).isEqualTo('B')
        nodesMap.get('B')!!.markAsComplete()
        assertThat(directedGraph.getNextNodeReadyForWork().id).isEqualTo('D')
        nodesMap.get('D')!!.markAsComplete()
        assertThat(directedGraph.getNextNodeReadyForWork().id).isEqualTo('E')
    }

    @Test
    fun testNodeCost() {
        val node = Node('Z', 10)
        assertThat(node.cost).isEqualTo(36)
        assertThat(node.remainingWork).isEqualTo(36)
        assertThat(node.isWorkComplete).isEqualTo(false)
        node.registerWork()
        assertThat(node.remainingWork).isEqualTo(35)
        node.registerWork(35)
        assertThat(node.remainingWork).isEqualTo(0)
        assertThat(node.isWorkComplete).isEqualTo(true)
    }

    @Test
    fun testNodeWork() {
        val node = Node('Z', 10)
        assertThat(node.cost).isEqualTo(36)
    }

}