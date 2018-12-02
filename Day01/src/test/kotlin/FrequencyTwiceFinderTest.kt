import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class FrequencyTwiceFinderTest {

    /**
     * +1, -1 first reaches 0 twice.
     */
    @Test fun testZero() {
        val classUnderTest = FrequencyTwiceFinder()
        val list = listOf(+1, -1)
        assertThat(classUnderTest.find(list), `is`(equalTo(0)))
    }

    /**
     * +3, +3, +4, -2, -4 first reaches 10 twice.
     */
    @Test fun testTen() {
        val classUnderTest = FrequencyTwiceFinder()
        val list = listOf(+3, +3, +4, -2, -4)
        assertThat(classUnderTest.find(list), `is`(equalTo(10)))
    }

    /**
     * -6, +3, +8, +5, -6 first reaches 5 twice.
     */
    @Test fun testFive() {
        val classUnderTest = FrequencyTwiceFinder()
        val list = listOf(-6, +3, +8, +5, -6)
        assertThat(classUnderTest.find(list), `is`(equalTo(5)))
    }

    /**
     * +7, +7, -2, -7, -4 first reaches 14 twice.
     */
    @Test fun testFourteen() {
        val classUnderTest = FrequencyTwiceFinder()
        val list = listOf(+7, +7, -2, -7, -4)
        assertThat(classUnderTest.find(list), `is`(equalTo(14)))
    }
}
