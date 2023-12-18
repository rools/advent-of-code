package year2023

import kotlin.math.absoluteValue

object Day18 {
    fun solve1(input: List<String>): Long {
        val plan = input.map { line ->
            val parts = line.split(' ')
            Command(
                direction = parts[0],
                count = parts[1].toLong()
            )
        }
        return calculateArea(plan)
    }

    fun solve2(input: List<String>): Long {
        val plan = input.map { line ->
            val hex = line.substringAfter('#').dropLast(1)
            Command(
                direction = when (hex.last()) {
                    '0' -> "R"
                    '1' -> "D"
                    '2' -> "L"
                    else -> "U"
                },
                count = hex.dropLast(1).toLong(16)
            )
        }
        return calculateArea(plan)
    }

    private fun calculateArea(plan: List<Command>): Long {
        var x = 0L
        var y = 0L

        var sign = 1
        var area = 0L
        var edgeLength = 0L

        plan.forEach { command ->
            val lastX = x
            val lastY = y

            when (command.direction) {
                "R" -> x += command.count
                "D" -> y += command.count
                "L" -> x -= command.count
                else -> y -= command.count
            }

            edgeLength += (lastX - x).absoluteValue + (lastY - y).absoluteValue
            area += sign * x * y
            sign *= -1
        }

        return area.absoluteValue + edgeLength / 2 + 1
    }

    private data class Command(
        val direction: String,
        val count: Long
    )
}