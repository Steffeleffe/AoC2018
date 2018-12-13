import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day08Test {
    @Test
    fun testPart1Answer() {
        assertThat(Day08().part1Answer()).isEqualTo(138)
    }

    @Test
    fun testPart2Answer() {
        assertThat(Day08().part2Answer()).isEqualTo(66)
    }

    @Nested
    inner class ParseNodeTest {
        @Test
        fun `containing only meta data`() {
            val input = listOf(0, 1, 99).iterator()
            val expected = Day08.Node(metadataEntries = mutableListOf(99))
            assertThat(Day08().parseNode(input)).isEqualTo(expected)
        }

        @Test
        fun `containing a single child node`() {
            val input = listOf(1, 1, 0, 1, 99, 2).iterator()
            val childNode = Day08.Node(metadataEntries = mutableListOf(99))
            val expectedRootNode = Day08.Node(listOf(childNode), listOf(2))
            assertThat(Day08().parseNode(input)).isEqualTo(expectedRootNode)
        }

        @Test
        fun `containing two child nodes`() {
            val input = listOf(2, 3, 0, 3, 10, 11, 12, 0, 1, 2, 1, 1, 2).iterator()
            val childNode1 = Day08.Node(metadataEntries = mutableListOf(10, 11, 12))
            val childNode2 = Day08.Node(metadataEntries = mutableListOf(2))
            val expectedRootNode = Day08.Node(listOf(childNode1, childNode2), listOf(1, 1, 2))
            assertThat(Day08().parseNode(input)).isEqualTo(expectedRootNode)
        }
    }

}