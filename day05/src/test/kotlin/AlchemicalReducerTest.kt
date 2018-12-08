import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class AlchemicalReducerTest {
    @Test
    fun testPart1() {
        val classUnderTest = AlchemicalReducer()
        val input = "dabAcCaCBAcCcaDA"
        assertThat(classUnderTest.reduce(input), equalTo("dabCBAcaDA"))
    }

    @Test
    fun testPart2() {
        val classUnderTest = AlchemicalReducer()
        val input = "dabAcCaCBAcCcaDA"
        assertThat(classUnderTest.improveReduce(input), equalTo("daDA"))

    }

    @Test
    fun testRemoveUnitFromPolymer() {
        val classUnderTest = AlchemicalReducer()
        val input = "dabAcCaCBAcCcaDA"
        assertThat(classUnderTest.removeUnitFromPolymer(input, 'a'), equalTo("dbcCCBcCcD"))
    }

}