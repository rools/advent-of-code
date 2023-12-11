package year2023

import kotlin.math.absoluteValue

object Day11 {
    fun solve1(input: List<String>): Long {
        return getSumOfDistances(input, expansion = 2)
    }

    fun solve2(input: List<String>, expansion: Int): Long {
        return getSumOfDistances(input, expansion)
    }

    private fun getSumOfDistances(universe: List<String>, expansion: Int): Long {
        val rowsToExpand = universe.indices
            .filter { index -> universe[index].all { it == '.' } }
            .toSet()

        val colsToExpand = universe[0].indices
            .filter { index -> universe.all { it[index] == '.' } }
            .toSet()

        val galaxies = buildList {
            var y = 0L
            repeat(universe.size) { row ->
                y += if (row in rowsToExpand) expansion else 1
                var x = 0L
                repeat(universe[0].length) { col ->
                    x += if (col in colsToExpand) expansion else 1
                    if (universe[row][col] == '#') add(x to y)
                }
            }
        }

        return galaxies.sumOf { galaxy1 ->
            galaxies.sumOf { galaxy2 ->
                (galaxy1.first - galaxy2.first).absoluteValue +
                        (galaxy1.second - galaxy2.second).absoluteValue
            }
        } / 2
    }
}