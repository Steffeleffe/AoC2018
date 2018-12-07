import java.time.LocalDateTime

/**
 * Claim format in input file:
 * [<date time>] <message>
 */
fun String.toLogEntry(): LogEntry {
    val regex = """\[(.*) (.*)] (.*)""".toRegex()
    val matchResult = regex.find(this)
    val (date, time, message) = matchResult!!.destructured
    val dateTime = LocalDateTime.parse("${date}T${time}:00")
    return LogEntry(dateTime, message)
}

data class LogEntry(val date: LocalDateTime,
                    val message: String)