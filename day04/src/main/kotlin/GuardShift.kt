import java.time.LocalDate

data class GuardShift(val day: LocalDate,
                      val guardId: Int,
                      var sleepingMinutes: List<Int> = emptyList()) {
    private var sleepStarted: Int = -1

    fun fallsAsleep(minute: Int) {
        sleepStarted = minute
    }

    fun wakesUp(minute: Int) {
        require(sleepStarted > -1)
        sleepingMinutes = sleepingMinutes.plus(sleepStarted until minute)
    }

    fun timeAsleepDuringShift(): Int {
        return sleepingMinutes.size
    }
}

