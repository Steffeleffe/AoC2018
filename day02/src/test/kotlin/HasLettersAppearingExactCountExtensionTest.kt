import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

/**
 * Tests the String extension function hasLettersAppearingExactCount.
 */
class HasLettersAppearingExactCountExtensionTest {
    @Test
    fun hasLettersAppearingExactlyTwice_no() {
        val string = "abcdef"
        MatcherAssert.assertThat(string.hasLettersAppearingExactCount(2), CoreMatchers.`is`(Matchers.equalTo(false)))
    }

    @Test
    fun hasLettersAppearingExactlyTwice_yes() {
        val string = "abbcde"
        MatcherAssert.assertThat(string.hasLettersAppearingExactCount(2), CoreMatchers.`is`(Matchers.equalTo(true)))
    }

    @Test
    fun hasLettersAppearingExactlyThreeTimes_no() {
        val string = "abcdef"
        MatcherAssert.assertThat(string.hasLettersAppearingExactCount(3), CoreMatchers.`is`(Matchers.equalTo(false)))
    }

    @Test
    fun hasLettersAppearingExactlyThreeTimes_yes() {
        val string = "abcccd"
        MatcherAssert.assertThat(string.hasLettersAppearingExactCount(3), CoreMatchers.`is`(Matchers.equalTo(true)))
    }
}