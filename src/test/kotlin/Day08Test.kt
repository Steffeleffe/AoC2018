import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day08Test {
    @Test
    fun testPart1Answer() {
        MatcherAssert.assertThat(Day08().part1Answer(), CoreMatchers.equalTo(138))
    }

    @Test
    fun testPart2Answer() {
        MatcherAssert.assertThat(Day08().part2Answer(), CoreMatchers.equalTo(66))
    }

    @Test
    fun testParseNodeOnlyWithMetadata() {
        val input = listOf(0, 1, 99).iterator()
        val expected = Day08.Node(metadataEntries = mutableListOf(99))
        assertThat(Day08().parseNode(input), equalTo(expected))
    }

    @Test
    fun testParseNodeOnlyWithSingleChildNode() {
        val input = listOf(1, 1, 0, 1, 99, 2).iterator()
        val childNode = Day08.Node(metadataEntries = mutableListOf(99))
        val expectedRootNode = Day08.Node(listOf(childNode), listOf(2))
        assertThat(Day08().parseNode(input), equalTo(expectedRootNode))
    }

    @Test
    fun testParseNodeOnlyWithTwoChildNodes() {
        val input = listOf(2, 3, 0, 3, 10, 11, 12, 0, 1, 2, 1, 1, 2).iterator()
        val childNode1 = Day08.Node(metadataEntries = mutableListOf(10, 11, 12))
        val childNode2 = Day08.Node(metadataEntries = mutableListOf(2))
        val expectedRootNode = Day08.Node(listOf(childNode1, childNode2), listOf(1, 1, 2))
        assertThat(Day08().parseNode(input), equalTo(expectedRootNode))
    }

}