fun main(args: Array<String>) {
    println("Day07")
    println("Part1: ${Day07().part1Answer()}")
    println("Part2: ${Day07().part2Answer(numberOfWorkers = 5, baseDuration = 60)}")
    println()

    println("Day08")
    println("Part1: ${Day08().part1Answer()}")
    println("Part2: ${Day08().part2Answer()}")
    println()

    println("Day09")
    println("Part1: ${Day09().solve(410, 72059)}")
    println("Part2: ${Day09().solve(410, 7205900)}")
    println()

    println("Day10")
    println("Part1:")
    Day10().part1Answer().forEach { println(it) }
    println("Part2: ${Day10().part2Answer()}")

}


