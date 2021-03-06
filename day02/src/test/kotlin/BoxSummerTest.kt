/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import java.io.File
import kotlin.test.Test

class BoxChecksummerTest {

    /**
     * Processing testinput_part1.txt results in  3
     */
    @Test fun testInput() {
        val classUnderTest = BoxChecksummer()
        val inputBoxes : List<String> = File(ClassLoader.getSystemResource("testinput_part1.txt").file)
                .useLines { it.toList() }
        assertThat(classUnderTest.calculate(inputBoxes), `is`(equalTo(12)))
    }

    /**
     * There should be 4 box ids in testinput_part1.txt with at least only letter appearing exactly twice
     */
    @Test fun countBoxesWithLettersExactlyTwice() {
        val classUnderTest = BoxChecksummer()
        val inputBoxes : List<String> = File(ClassLoader.getSystemResource("testinput_part1.txt").file)
                .useLines { it.toList() }
        assertThat(classUnderTest.countBoxesWithLettersAppearingExactlyTwice(inputBoxes), `is`(equalTo(4)))
    }

    /**
     * There should be 3 box ids in testinput_part1.txt with at least only letter appearing exactly three times
     */
    @Test fun countBoxesWithLettersExactlyThreeTimes() {
        val classUnderTest = BoxChecksummer()
        val inputBoxes : List<String> = File(ClassLoader.getSystemResource("testinput_part1.txt").file)
                .useLines { it.toList() }
        assertThat(classUnderTest.countBoxesWithLettersAppearingExactlyThreeTimes(inputBoxes), `is`(equalTo(3)))
    }

}
