import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class GetCommonCharactersExtensionTest {
    @Test
    fun testGetCommonCharacters_three() {
        val a = "abcde"
        val b = "axcye"
        assertThat(a.getCommonCharacters(b), `is`(equalTo("ace")))
    }

    @Test
    fun testGetCommonCharacters_four() {
        val a = "fghij"
        val b = "fguij"
        assertThat(a.getCommonCharacters(b), `is`(equalTo("fgij")))
    }

    @Test
    fun testGetCommonCharacters_none() {
        val a = "abcde"
        val b = "fghij"
        assertThat(a.getCommonCharacters(b), `is`(equalTo("")))
    }
}