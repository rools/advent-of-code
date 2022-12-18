package year2022

import kotlin.math.absoluteValue
import kotlin.math.sign

object Day15 {
    private val regex = Regex("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)")

    fun solve1(input: List<String>, targetY: Int): Int {
        val values = mutableMapOf<Int, Char>()

        input.forEach { line ->
            val match = regex.matchEntire(line)!!
            val sensorX = match.groupValues[1].toInt()
            val sensorY = match.groupValues[2].toInt()
            val beaconX = match.groupValues[3].toInt()
            val beaconY = match.groupValues[4].toInt()

            if (beaconY == targetY) {
                values[beaconX] = 'B'
            }

            val distance = (sensorX - beaconX).absoluteValue + (sensorY - beaconY).absoluteValue

            val xDist = (distance - (sensorY - targetY).absoluteValue)
            val minX = sensorX - xDist
            val maxX = sensorX + xDist

            (minX..maxX).forEach { x ->
                if (x !in values) values[x] = '#'
            }
        }

        return values.count { it.value == '#' }
    }

    fun solve2(input: List<String>): Long {
        val sensors = mutableListOf<Sensor>()

        input.forEach { line ->
            val match = regex.matchEntire(line)!!
            val sensorX = match.groupValues[1].toInt()
            val sensorY = match.groupValues[2].toInt()
            val beaconX = match.groupValues[3].toInt()
            val beaconY = match.groupValues[4].toInt()

            val distance = (sensorX - beaconX).absoluteValue + (sensorY - beaconY).absoluteValue

            sensors += Sensor(sensorX, sensorY, distance)
        }

        var tuningFrequency = 0L

        sensors.forEach { s1 ->
            sensors.forEach { s2 ->
                val dist = (s1.x - s2.x).absoluteValue + (s1.y - s2.y).absoluteValue
                if (dist > s1.distance + s2.distance) return@forEach
                if (dist < s1.distance || dist < s2.distance) return@forEach

                run {
                    val dirX = (s2.x - s1.x).sign
                    val dirY = (s2.y - s1.y).sign
                    var x: Double = s1.x.toDouble()
                    var y: Double = (s1.y + (s1.distance + 1) * dirY).toDouble()
                    val distDiff = (s2.distance + 1) - ((x - s2.x).absoluteValue + (y - s2.y).absoluteValue)
                    x -= 0.5 * distDiff * dirX
                    y -= 0.5 * distDiff * dirY

                    val candidates = listOf(
                        x.toInt() to y.toInt(),
                        (x + 1).toInt() to y.toInt(),
                        x.toInt() to (y + 1).toInt(),
                        (x + 1).toInt() to (y + 1).toInt()
                    )

                    candidates.forEach { (x, y) ->
                        if (x < 0 || x > 4000000 || y < 0 || y > 4000000) {
                            return@forEach
                        }
                        var tooClose = false
                        sensors.forEach { s3 ->
                            val dist3 = (s3.x - x).absoluteValue + (s3.y - y).absoluteValue
                            if (dist3 <= s3.distance) {
                                tooClose = true
                                return@forEach
                            }

                        }
                        if (!tooClose) {
                            tuningFrequency = x.toLong() * 4000000 + y.toLong()
                        }
                    }
                }
            }
        }

        return tuningFrequency
    }

    private data class Sensor(
        val x: Int,
        val y: Int,
        val distance: Int
    )
}