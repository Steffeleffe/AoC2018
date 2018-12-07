import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.TemporalAccessor
import java.util.*

class LogEntryParserExtensionTest {
    @Test
    fun testToFabricClaim() {
        val a = "[1518-11-03 00:05] Guard #10 begins shift"

        val expected = LogEntry(LocalDateTime.of(1518, 11, 3, 0, 5), "Guard #10 begins shift")
        assertThat(a.toLogEntry(), `is`(equalTo(expected)))
    }

}