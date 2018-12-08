import java.io.File

fun main(args: Array<String>) {
    val polymerString: String = File(ClassLoader.getSystemResource("input.txt").file)
            .useLines { it.toList() }
            .get(0)
    println("Part1: ${AlchemicalReducer().reduce(polymerString).length}")
    println("Part2: ${AlchemicalReducer().improveReduce(polymerString).length}")

}
