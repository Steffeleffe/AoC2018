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
        assertThat(Day07().parseStepDependencyString("Step A must be finished before step B can begin."), equalTo(Edge('A', 'B')))
    }

    @Test
    fun testNodesInGraph() {
        val input = mutableSetOf(Edge('A', 'D'), Edge('B', 'E'), Edge('D', 'E'))
        assertThat(DirectedGraph(input).nodes, Matchers.containsInAnyOrder('A', 'B', 'D', 'E'))
    }

    @Test
    fun testCountIncomingEdges() {
        val input = mutableSetOf(Edge('A', 'D'), Edge('B', 'E'), Edge('D', 'E'))
        assertThat(DirectedGraph(input).countIncomingEdges('A'), equalTo(0))
        assertThat(DirectedGraph(input).countIncomingEdges('B'), equalTo(0))
        assertThat(DirectedGraph(input).countIncomingEdges('D'), equalTo(1))
        assertThat(DirectedGraph(input).countIncomingEdges('E'), equalTo(2))
    }

    @Test
    fun testRemoveNode() {
        val input = mutableSetOf(Edge('A', 'D'), Edge('B', 'E'), Edge('D', 'E'))
        val directedGraph = DirectedGraph(input)
        directedGraph.removeNode('A')
        assertThat(directedGraph.nodes, Matchers.containsInAnyOrder('B', 'D', 'E'))
        assertThat(directedGraph.edges, Matchers.containsInAnyOrder(Edge('B', 'E'), Edge('D', 'E')))
    }

    @Test
    fun testAllButLastNode() {
        val input = mutableSetOf(Edge('A', 'D'), Edge('B', 'E'), Edge('D', 'E'))
        val directedGraph = DirectedGraph(input)
        directedGraph.removeNode('A')
        directedGraph.removeNode('B')
        directedGraph.removeNode('D')
        assertThat(directedGraph.nodes, Matchers.containsInAnyOrder('E'))
        assertThat(directedGraph.edges, Matchers.empty())
    }

}