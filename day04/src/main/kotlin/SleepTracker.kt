import java.io.File

class SleepTracker {

    fun getGuardShiftsFromLogEntries(logEntries: List<LogEntry>): List<GuardShift> {
        val sortedEntries = logEntries.sortedBy { it.date }
        val guardShifts: MutableList<GuardShift> = mutableListOf()
        var currentGuardShift: GuardShift? = null
        for (logEntry in sortedEntries) {
            when {
                logEntry.message.contains("begins shift") -> {
                    val regex = """Guard #(\d*) begins shift""".toRegex()
                    val matchResult = regex.find(logEntry.message)
                    val (guard) = matchResult!!.destructured
                    val toLocalDate = logEntry.date.plusHours(2).toLocalDate() // plus hours to move 23:xx to next day
                    currentGuardShift = GuardShift(toLocalDate, guard.toInt())
                    guardShifts.add(currentGuardShift)

                }
                logEntry.message.contains("falls asleep") -> currentGuardShift!!.fallsAsleep(logEntry.date.minute)
                logEntry.message.contains("wakes up") -> currentGuardShift!!.wakesUp(logEntry.date.minute)
            }
        }

        return guardShifts
    }

    fun getGuardShiftsFromInput(resourceName : String): List<GuardShift> {
        return getGuardShiftsFromLogEntries(getLogEntriesFromInput(resourceName))
    }

    fun getLogEntriesFromInput(resourceName: String): List<LogEntry> {
       return File(ClassLoader.getSystemResource(resourceName).file)
                .useLines { it.toList() }
                .map { it.toLogEntry() }
    }

    fun part1Answer(inputShifts: List<GuardShift>): Int {
        val whichGuardIsMostAtSleep = whichGuardIsMostAtSleep(inputShifts)
        return whichGuardIsMostAtSleep * whatMinuteDoesGuardSleepTheMost(inputShifts, whichGuardIsMostAtSleep)!!.key
    }

    fun whichGuardIsMostAtSleep(inputShifts: List<GuardShift>): Int {
        val guardToSleepingTime: MutableMap<Int, Int> = mutableMapOf()
        for ((guardId, list) in inputShifts.groupBy { it.guardId }) {
            guardToSleepingTime.putIfAbsent(guardId, list.map { it.timeAsleepDuringShift()}.sum())
        }

        return guardToSleepingTime.maxBy { it.value }!!.key
    }

    fun whatMinuteDoesGuardSleepTheMost(inputShifts: List<GuardShift>, guardId: Int): Map.Entry<Int, Int>? {
        val sleepingMinutesForGuard = inputShifts.filter { it.guardId == guardId }.map {it.sleepingMinutes}.flatten()
        val minutesCountMap = sleepingMinutesForGuard.groupingBy { it }.eachCount()
        return minutesCountMap.maxBy { it.value }
    }

    fun part2Answer(inputShifts: List<GuardShift>): Int {
        val guardToSleepingTime: MutableMap<Int, Int> = mutableMapOf()
        for (guardId in inputShifts.map { it.guardId }.toSet()) {
            val whatMinuteDoesGuardSleepTheMostCount = whatMinuteDoesGuardSleepTheMost(inputShifts, guardId)?.value
            guardToSleepingTime.putIfAbsent(guardId, whatMinuteDoesGuardSleepTheMostCount ?: 0)
        }
        val guardId = guardToSleepingTime.maxBy { it.value }!!.key
        return guardId * (whatMinuteDoesGuardSleepTheMost(inputShifts, guardId)!!.key)
    }

}
