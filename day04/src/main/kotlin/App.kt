import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main(args: Array<String>) {
    val guardShiftsFromInput = SleepTracker().getGuardShiftsFromInput("input.txt")

    println("Part1: ${SleepTracker().part1Answer(guardShiftsFromInput)}")
    println("Part2: ${SleepTracker().part2Answer(guardShiftsFromInput)}")

}


