import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Visually, these claim the following areas:
........
...2222.
...2222.
.11XX22.
.11XX22.
.111133.
.111133.
........
 * The four square inches marked with X are claimed by both 1 and 2. (Claim 3, while adjacent to the others, does not
 * overlap either of them.)
 */
class FabricSquareCounterTest {

    /**
     * Processing testinput_part1.txt results in  4
     */
    @Test
    fun testPart1() {
        val classUnderTest = FabricSquareCounter()
        val inputClaims: List<FabricClaim> = getInputFabricClaims()
        assertThat(classUnderTest.countSquaresWithDuplicateClaim(inputClaims), `is`(equalTo(4)))
    }


    /**
     * Processing testinput_part1.txt results in  4
     */
    @Test
    fun testPart2() {
        val classUnderTest = FabricSquareCounter()
        val inputClaims: List<FabricClaim> = getInputFabricClaims()
        val findFabricClaimWhereAreSquaresAreClaimedOnlyOnce: Set<Int> = classUnderTest.findFabricClaimWhereAllSquaresAreClaimedByNoOneElse(inputClaims)

        assertThat(findFabricClaimWhereAreSquaresAreClaimedOnlyOnce, contains(3))
    }

    fun getInputFabricClaims(): List<FabricClaim> =
            File(ClassLoader.getSystemResource("testinput_part1.txt").file)
                    .useLines { it.toList() }
                    .map { it.toFabricClaim() }

    /**
    ........
    ........
    ...11...
    ...11...
    ...11...
    ........
    ........
    ........
     */
    @Test
    fun testGetSquaresClaimed() {
        val classUnderTest = FabricSquareCounter()
        val claim = FabricClaim(1, 3, 2, 2, 3)
        val expected: List<SquarePosition> =
                listOf(SquarePosition(Pair(3, 2)), SquarePosition(Pair(3, 3)), SquarePosition(Pair(3, 4)),
                        SquarePosition(Pair(4, 2)), SquarePosition(Pair(4, 3)), SquarePosition(Pair(4, 4)))
        println("Expected: $expected")
        val actual = classUnderTest.getSquaresClaimed(claim)
        println("Actual:   $actual")
        assertTrue(actual.equals(expected))
    }

    @Test
    fun testCountClaimsOnEachSquare() {
        val classUnderTest = FabricSquareCounter()
        val inputClaims: List<FabricClaim> = getInputFabricClaims()
        val countClaimsOnEachSquare = classUnderTest.findClaimsOnEachSquarePosition(inputClaims)
        assertThat(countClaimsOnEachSquare.size, `is`(equalTo(32)))
        assertThat(countClaimsOnEachSquare.get(SquarePosition(Pair(2, 2))), `is`(nullValue()))
        assertThat(countClaimsOnEachSquare.get(SquarePosition(Pair(3, 2)))?.count, `is`(equalTo(1)))
        assertThat(countClaimsOnEachSquare.get(SquarePosition(Pair(3, 3)))?.count, `is`(equalTo(2)))
    }

}
