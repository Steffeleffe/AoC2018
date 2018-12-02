import java.io.File

fun main(args: Array<String>) {
    val inputFrequencies : List<Int> = File(ClassLoader.getSystemResource("input.txt").file)
            .useLines { it.toList() }
            .map { it.toInt() }
    println("Part1: ${FrequencySummer().sum(inputFrequencies)}")
    println("Part2: ${FrequencyTwiceFinder().find(inputFrequencies)}")

}
