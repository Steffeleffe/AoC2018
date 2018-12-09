import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

class LargestAreaFinderTest {

    @Test
    fun testPart1() {
        val classUnderTest = LargestAreaFinder()
        val inputCoordinates = getInputCoordinates()
        assertThat(classUnderTest.findLargestArea(inputCoordinates), equalTo(17))
    }


    @Test
    fun testFindAreaSpannedByInput() {
        val classUnderTest = LargestAreaFinder()
        val inputCoordinates: List<Coordinate> = getInputCoordinates()
        val expected = Pair(Coordinate(1, 1), Coordinate(8, 9))
        assertThat(classUnderTest.findAreaSpannedByInput(inputCoordinates), equalTo(expected))

    }

    @Test
    fun testFindNearestInputCoordinate_single() {
        val classUnderTest = LargestAreaFinder()
        val inputCoordinates: List<Coordinate> = getInputCoordinates()
        val coordinate = Coordinate(5, 2)
        assertThat(classUnderTest.findNearestInputCoordinate(coordinate, inputCoordinates).coordinate,
                equalTo(Coordinate(5, 5)))
    }

    @Test
    fun testFindNearestInputCoordinate_shared() {
        val classUnderTest = LargestAreaFinder()
        val inputCoordinates: List<Coordinate> = getInputCoordinates()
        val coordinate = Coordinate(1, 4)
        assertThat(classUnderTest.findNearestInputCoordinate(coordinate, inputCoordinates).coordinate,
                equalTo(Coordinate(-1, -1)))

    }

    @Test
    fun testFindInfiniteAreaCoordinates() {
        val classUnderTest = LargestAreaFinder()
        val inputCoordinates: List<Coordinate> = getInputCoordinates()
        val coordinateToNearestInputCoordinate = classUnderTest.calculateCoordinateMap(inputCoordinates)
        val expected: Set<Coordinate> = setOf(Coordinate(1, 1), Coordinate(1, 6), Coordinate(8, 3), Coordinate(8, 9), Coordinate(-1, -1))
        assertThat(classUnderTest.findInfiniteAreaCoordinates(coordinateToNearestInputCoordinate).size, equalTo(5))
        assertEquals<Set<Coordinate>>(expected, classUnderTest.findInfiniteAreaCoordinates(coordinateToNearestInputCoordinate))
    }

    @Test
    fun testPart2() {
        val classUnderTest = LargestAreaFinder()
        val inputCoordinates = getInputCoordinates()
        assertThat(classUnderTest.findCoordinatesWithinTotalDistance(inputCoordinates, 32), equalTo(16))
    }


    private fun getInputCoordinates() =
            File(ClassLoader.getSystemResource("testinput.txt").file)
                    .useLines { it.toList() }
                    .map { toCoordinate(it) }
                    .toList()


}