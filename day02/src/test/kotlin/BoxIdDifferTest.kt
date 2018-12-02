import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import java.io.File

class BoxIdDifferTest {

    /**
     * Processing testinput_part2.txt results in  "fgij"
     */
    @Test
    fun testFindCommonPrototypeBoxIdsFromInput() {
        val classUnderTest = BoxIdDiffer()
        val inputBoxes: List<String> = File(ClassLoader.getSystemResource("testinput_part2.txt").file)
                .useLines { it.toList() }
        assertThat(classUnderTest.findCommonPrototypeBoxIds(inputBoxes), `is`(equalTo("fgij")))
    }

    /**
     * Find the related boxes in the testinput_part2 results in  list of "fghij", "fguij".
     */
    @Test
    fun testIdentifyRelatedBoxesFromInput() {
        val classUnderTest = BoxIdDiffer()
        val inputBoxes: List<String> = File(ClassLoader.getSystemResource("testinput_part2.txt").file)
                .useLines { it.toList() }
        assertThat(classUnderTest.identifyRelatedBoxes(inputBoxes), containsInAnyOrder("fghij", "fguij"))
    }

    /**
     * Check that method works when match in last two entries on list.
     */
    @Test
    fun testIdentifyRelatedBoxesEdgeCase_endOfList() {
        val classUnderTest = BoxIdDiffer()
        val boxIds = listOf("xxxx", "yyyy", "zzzz", "aaaa", "aaab")
        assertThat(classUnderTest.identifyRelatedBoxes(boxIds), containsInAnyOrder("aaaa", "aaab"))
    }

    /**
     * Check that method works when match in first two entries on list.
     */
    @Test
    fun testIdentifyRelatedBoxesEdgeCase_startOfList() {
        val classUnderTest = BoxIdDiffer()
        val boxIds = listOf("aaaa", "aaab", "xxxx", "yyyy", "zzzz")
        assertThat(classUnderTest.identifyRelatedBoxes(boxIds), containsInAnyOrder("aaaa", "aaab"))
    }



}
