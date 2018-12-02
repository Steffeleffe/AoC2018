import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class AlmostTheSameExtensionTest {
    @Test
    fun testAlmostTheSame_no() {
        val a = "abcde"
        val b = "axcye"
        assertThat(a.almostTheSame(b), `is`(equalTo(false)))
    }

    @Test
    fun testAlmostTheSame_yes() {
        val a = "fghij"
        val b = "fguij"
        assertThat(a.almostTheSame(b), `is`(equalTo(true)))
    }
}