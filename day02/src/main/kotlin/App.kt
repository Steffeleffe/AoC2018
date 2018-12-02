import java.io.File

fun main(args: Array<String>) {
    val boxIds : List<String> = File(ClassLoader.getSystemResource("input.txt").file)
            .useLines { it.toList() }
    println("Part1: ${BoxChecksummer().calculate(boxIds)}")
    println("Part2: ${BoxIdDiffer().findCommonPrototypeBoxIds(boxIds)}")

}
