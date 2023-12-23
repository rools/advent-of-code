package year2023

import lib.data.*
import lib.utils.positionOf
import lib.utils.rem
import lib.utils.toMatrix
import java.util.LinkedList
import java.util.Queue

object Day21 {
    fun solve1(input: List<String>, maxSteps: Int): Int {
        return measurePlotCount(input.toMatrix(), maxSteps)
    }

    fun solve2(input: List<String>, maxSteps: Int): Long {
        return calculatePlotCount(input.toMatrix(), maxSteps)
    }

    private fun calculatePlotCount(map: Matrix<Char>, maxSteps: Int): Long {
        val diffs = mutableListOf<Long>()
        val lastDiffs = mutableListOf<Long>()
        val lastDiffIncreases = mutableListOf<Long>()

        var lastCount = 0L
        var iteration = 0

        while (true) {
            if (diffs.size == map.rows) {
                val diffIncreases = diffs.zip(lastDiffs) { d, ld -> d - ld }

                if (diffIncreases.isNotEmpty() && diffIncreases == lastDiffIncreases) {
                    while (iteration <= maxSteps) {
                        diffs[iteration % diffs.size] += diffIncreases[iteration % diffIncreases.size]
                        lastCount += diffs[iteration % diffs.size]
                        iteration++
                    }
                    return lastCount
                }

                lastDiffs.clear()
                lastDiffs += diffs
                diffs.clear()

                lastDiffIncreases.clear()
                lastDiffIncreases += diffIncreases
            }

            val count = measurePlotCount(map, iteration)

            diffs += count - lastCount
            lastCount = count.toLong()

            iteration++
        }
    }

    private fun measurePlotCount(map: Matrix<Char>, maxSteps: Int): Int {
        val positions: Queue<Vec2> = LinkedList()
        val counts: Queue<Int> = LinkedList()

        val start = map.positionOf('S')
        map[start] = '.'

        positions += start
        counts += 0

        val visited = mutableMapOf<Vec2, Int>()
        var plots = 0L

        while (positions.isNotEmpty()) {
            val position = positions.remove()
            val count = counts.remove()

            if (position in visited) continue

            plots++
            visited[position] = count

            if (count == maxSteps) continue

            Direction.values().forEach { direction ->
                val nextPosition = position + direction
                if (map[nextPosition.wrap(map)] != '.') return@forEach
                positions += nextPosition
                counts += count + 1
            }
        }

        map[start] = 'S'

        return visited.values.count { it % 2 == maxSteps % 2 }
    }

    private fun Vec2.wrap(matrix: Matrix<*>): Vec2 {
        return (this % matrix.dimension + matrix.dimension) % matrix.dimension
    }
}