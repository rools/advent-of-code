package year2022

import kotlin.math.max
import kotlin.math.min

object Day14 {
    fun solve1(input: List<String>): Int {
        val cave = mutableMapOf<Pair<Int, Int>, Char>()

        input.forEach { line ->
            val points = line.split(" -> ")
                .map { it.split(",") }
                .map { it[0].toInt() to it[1].toInt() }

            points.zipWithNext { a, b ->
                if (a.first == b.first) {
                    (min(a.second, b.second)..max(a.second, b.second)).forEach { y ->
                        cave[a.first to y] = '#'
                    }
                } else {
                    (min(a.first, b.first)..max(a.first, b.first)).forEach { x ->
                        cave[x to a.second] = '#'
                    }
                }
            }
        }

        var sandCount = 0

        while (true) {
            var sandX = 500
            var sandY = 0

            while (true) {
                if (sandY > 1000) {
                    return sandCount
                } else if (sandX to sandY + 1 !in cave) {
                    sandY++
                } else if (sandX - 1 to sandY + 1 !in cave) {
                    sandY++
                    sandX--
                } else if (sandX + 1 to sandY + 1 !in cave) {
                    sandY++
                    sandX++
                } else {
                    sandCount++
                    cave[sandX to sandY] = 'o'
                    break
                }
            }
        }
    }

    fun solve2(input: List<String>): Int {
        val cave = mutableMapOf<Pair<Int, Int>, Char>()

        input.forEach { line ->
            val points = line.split(" -> ")
                .map { it.split(",") }
                .map { it[0].toInt() to it[1].toInt() }

            points.zipWithNext { a, b ->
                if (a.first == b.first) {
                    (min(a.second, b.second)..max(a.second, b.second)).forEach { y ->
                        cave[a.first to y] = '#'
                    }
                } else {
                    (min(a.first, b.first)..max(a.first, b.first)).forEach { x ->
                        cave[x to a.second] = '#'
                    }
                }
            }
        }

        val floorY = cave.keys.maxOf { it.second } + 1

        var sandCount = 0

        while (true) {
            var sandX = 500
            var sandY = 0

            while (true) {
                if (sandY >= floorY) {
                    sandCount++
                    cave[sandX to sandY] = 'o'
                    break
                } else if (sandX to sandY + 1 !in cave) {
                    sandY++
                } else if (sandX - 1 to sandY + 1 !in cave) {
                    sandY++
                    sandX--
                } else if (sandX + 1 to sandY + 1 !in cave) {
                    sandY++
                    sandX++
                } else {
                    sandCount++
                    cave[sandX to sandY] = 'o'

                    if (sandX == 500 && sandY == 0) {
                        return sandCount
                    }

                    break
                }
            }
        }
    }
}