import java.io.File

class Day10 {

    fun readLightsFromInput(): SkyLights {
        val lights = File(ClassLoader.getSystemResource("day10_input.txt").file)
                .useLines { it.toList() }
                .map { parseLightString(it) }
                .toList()
        return SkyLights(lights)
    }

    /**
     * Parse: position=< 9,  1> velocity=< 0,  2>
     */
    fun parseLightString(inputString: String): Light {
        val regex = """position=<(.+),(.+)> velocity=<(.+),(.+)>""".toRegex()
        val matchResult = regex.find(inputString)
        val (posX, posY, velX, velY) = matchResult!!.destructured
        return Light(
                Light.Position(posX.trim().toInt(), posY.trim().toInt()),
                Light.Velocity(velX.trim().toInt(), velY.trim().toInt()))
    }

    fun part1Answer(): List<String> {
        val readLightsFromInput = readLightsFromInput()
        val smallestSkyTime = findSmallestSky(readLightsFromInput)
        return readLightsFromInput.printableSkyChart(smallestSkyTime)
    }

    fun part2Answer(): Int {
        val readLightsFromInput = readLightsFromInput()
        return findSmallestSky(readLightsFromInput)
    }

    /**
     * Find the most compact sky dimensions, and return at which time this happens
     */
    private fun findSmallestSky(readLightsFromInput: SkyLights): Int {
        var lastSkyArea: Long = Long.MAX_VALUE
        var timeStep = 0
        while (true) {
            val totalSkyAreaAtTime = readLightsFromInput.skyDimension(timeStep).totalArea
            if (totalSkyAreaAtTime < lastSkyArea) {
                timeStep++
                lastSkyArea = totalSkyAreaAtTime
                continue
            } else {
                break
            }
        }
        return timeStep - 1
    }

    data class SkyLights(val lights: List<Light>) {
        fun printableSkyChart(timeStep: Int = 0): List<String> {
            val skyChart: MutableList<String> = mutableListOf()
            val skyDimensionAtTime = skyDimension(timeStep)
            for (y in skyDimensionAtTime.top..skyDimensionAtTime.bottom) {
                val rowOfLights: MutableList<Char> = mutableListOf()
                for (x in skyDimensionAtTime.left..skyDimensionAtTime.right) {
                    val lightsAtTime = lights.map { it.getPositionAtTime(timeStep) }.toList()
                    rowOfLights.add(if (lightsAtTime.any { it == Light.Position(x, y) }) '#' else '.')
                }
                skyChart.add(rowOfLights.joinToString(separator = ""))
            }
            return skyChart
        }

        fun skyDimension(timeStep: Int = 0): SkyDimension {
            val lightPositions = lights.map { it.getPositionAtTime(timeStep) }
            return SkyDimension(
                    lightPositions.minBy { it.x }!!.x,
                    lightPositions.maxBy { it.x }!!.x,
                    lightPositions.minBy { it.y }!!.y,
                    lightPositions.maxBy { it.y }!!.y)
        }

        data class SkyDimension(val left: Int, val right: Int, val top: Int, val bottom: Int) {
            val totalArea get() : Long = (right - left + 1).toLong() * (bottom - top + 1).toLong()
        }
    }

    data class Light(val position: Position, val velocity: Velocity) {
        data class Position(val x: Int, val y: Int)
        data class Velocity(val x: Int, val y: Int)

        fun getPositionAtTime(timeSteps: Int): Position {
            return Position(
                    position.x + timeSteps * velocity.x,
                    position.y + timeSteps * velocity.y)
        }
    }

}