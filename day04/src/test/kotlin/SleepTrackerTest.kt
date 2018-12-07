import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasItem
import java.time.LocalDate
import kotlin.test.Test


class SleepTrackerTest {

    @Test
    fun testPart1Answer() {
        val classUnderTest = SleepTracker()
        val inputShifts: List<GuardShift> = classUnderTest.getGuardShiftsFromInput("testinput_part1.txt")
        assertThat(classUnderTest.part1Answer(inputShifts), `is`(equalTo(240)))
    }

    @Test
    fun testPart2Answer() {
        val classUnderTest = SleepTracker()
        val inputShifts: List<GuardShift> = classUnderTest.getGuardShiftsFromInput("testinput_part1.txt")
        assertThat(classUnderTest.part2Answer(inputShifts), `is`(equalTo(4455)))
    }

    @Test
    fun testWhichGuardIsMostAtSleep() {
        val classUnderTest = SleepTracker()
        val inputShifts: List<GuardShift> = classUnderTest.getGuardShiftsFromInput("testinput_part1.txt")
        assertThat(classUnderTest.whichGuardIsMostAtSleep(inputShifts), `is`(equalTo(10)))
    }

    @Test
    fun testWhatMinuteDoesGuardSleepTheMost() {
        val classUnderTest = SleepTracker()
        val inputShifts: List<GuardShift> = classUnderTest.getGuardShiftsFromInput("testinput_part1.txt")
        assertThat(classUnderTest.whatMinuteDoesGuardSleepTheMost(inputShifts, 10)!!.key, `is`(equalTo(24)))
    }

    @Test
    fun testGetGuardShiftsFromLogEntries() {
        val classUnderTest = SleepTracker()
        val inputClaims: List<LogEntry> = classUnderTest.getLogEntriesFromInput("testinput_part1.txt")
        val expected = GuardShift(LocalDate.of(1518, 11, 1), 10, (5..24).plus(30..54))
        assertThat(classUnderTest.getGuardShiftsFromLogEntries(inputClaims), hasItem(expected))
    }

}
