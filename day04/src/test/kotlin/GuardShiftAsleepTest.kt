import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import java.time.LocalDate

class GuardShiftAsleepTest {

    @Test
    fun testTimeAsleepDuringShift() {
        val shift = GuardShift(LocalDate.of(1518, 11, 1), 10, (5..24).plus(30..54))
        assertThat(shift.timeAsleepDuringShift(), `is`(equalTo(45)))
    }
}