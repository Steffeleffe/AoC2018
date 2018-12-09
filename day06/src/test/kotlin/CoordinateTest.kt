import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CoordinateTest {

    @Test
    fun testManhattenDistance() {
        val coordinate1 = Coordinate(3, 4)
        val coordinate2 = Coordinate(4, 2)
        assertThat(coordinate1.manhattenDistance(coordinate2), equalTo(3))
    }

}