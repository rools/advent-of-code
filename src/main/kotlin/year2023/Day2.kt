package year2023

import kotlin.math.max

object Day2 {
    fun solve1(input: List<String>): Int {
        return input.sumOf { line ->
            val gameId = line.substringBefore(':')
                .substringAfter(' ')
                .toInt()

            val maxCounts = getMaxCounts(line)
            if ((maxCounts["red"] ?: 0) <= 12 &&
                (maxCounts["green"] ?: 0) <= 13 &&
                (maxCounts["blue"] ?: 0) <= 14
            ) gameId else 0
        }
    }

    fun solve2(input: List<String>): Int {
        return input.sumOf { line ->
            val maxCounts = getMaxCounts(line)
            (maxCounts["red"] ?: 0) *
                    (maxCounts["green"] ?: 0) *
                    (maxCounts["blue"] ?: 0)
        }
    }

    private fun getMaxCounts(line: String): Map<String, Int> = buildMap {
        line.substringAfter(": ")
            .split("; ")
            .forEach { game ->
                game.split(", ")
                    .map { it.split(' ') }
                    .forEach { (count, color) ->
                        put(color, max(get(color) ?: 0, count.toInt()))
                    }
            }
    }
}