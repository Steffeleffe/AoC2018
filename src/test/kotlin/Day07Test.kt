import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test

class Day07Test {
    @Test
    fun testPart1Answer() {
        assertThat(Day07().part1Answer(), equalTo("CABDFE"))
    }

    @Test
    fun testPart2Answer() {
        assertThat(Day07().part2Answer(2, 0), equalTo(15))
    }

    @Test
    fun testParseStepDependencyString() {
        assertThat(Day07().parseStepDependencyString("Step A must be finished before step B can begin."), equalTo(EdgeInput('A', 'B')))
    }

    @Test
    fun testNodesInGraph() {
        val input = mutableSetOf(EdgeInput('A', 'D'), EdgeInput('B', 'E'), EdgeInput('D', 'E'))
        assertThat(DirectedGraph(input).nodes.map { it.id }, Matchers.containsInAnyOrder('A', 'B', 'D', 'E'))
    }

    @Test
    fun testIsNodeReadyForWork() {
        val input = mutableSetOf(EdgeInput('A', 'D'), EdgeInput('B', 'E'), EdgeInput('D', 'E'))
        val directedGraph = DirectedGraph(input)
        val nodesMap = directedGraph.nodes.map { it.id to it }.toMap()
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['A']!!), equalTo(true))
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['B']!!), equalTo(true))
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['D']!!), equalTo(false))
        assertThat(directedGraph.isNodeReadyForWork(nodesMap['E']!!), equalTo(false))
    }

    @Test
    fun testGetNextNodeReadyForWork() {
        val input = mutableSetOf(EdgeInput('A', 'D'), EdgeInput('B', 'E'), EdgeInput('D', 'E'))
        val directedGraph = DirectedGraph(input)
        val nodesMap = directedGraph.nodes.map { it.id to it }.toMap()
        assertThat(directedGraph.getNextNodeReadyForWork().id, equalTo('A'))
        nodesMap.get('A')!!.markAsComplete()
        assertThat(directedGraph.getNextNodeReadyForWork().id, equalTo('B'))
        nodesMap.get('B')!!.markAsComplete()
        assertThat(directedGraph.getNextNodeReadyForWork().id, equalTo('D'))
        nodesMap.get('D')!!.markAsComplete()
        assertThat(directedGraph.getNextNodeReadyForWork().id, equalTo('E'))
    }

    @Test
    fun testNodeCost() {
        val node = Node('Z', 10)
        assertThat(node.cost, equalTo(36))
        assertThat(node.remainingWork, equalTo(36))
        assertThat(node.isWorkComplete, equalTo(false))
        node.registerWork()
        assertThat(node.remainingWork, equalTo(35))
        node.registerWork(35)
        assertThat(node.remainingWork, equalTo(0))
        assertThat(node.isWorkComplete, equalTo(true))
    }

    @Test
    fun testNodeWork() {
        val node = Node('Z', 10)
        assertThat(node.cost, equalTo(36))
    }

}