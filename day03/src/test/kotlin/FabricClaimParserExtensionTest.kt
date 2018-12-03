import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class FabricClaimParserExtensionTest {
    @Test
    fun testToFabricClaim() {
        val a = "#123 @ 3,2: 5x4"
        val expected = FabricClaim(123, 3, 2, 5, 4)
        assertThat(a.toFabricClaim(), `is`(equalTo(expected)))
    }

}