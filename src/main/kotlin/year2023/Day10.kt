package year2023

import java.util.LinkedList
import java.util.Queue

object Day10 {
    fun solve1(input: List<String>): Int {
        return getDistances(input.toSketch())
            .maxOf { it.max() }
    }

    fun solve2(input: List<String>): Int {
        val sketch = input.toSketch()
        val distances = getDistances(sketch)
        var enclosedTiles = 0

        repeat(sketch.size) { y ->
            var isEnclosed = false
            var startingPipe = '.'
            repeat(sketch[0].size) { x ->
                if (distances[y][x] >= 0) {
                    if (sketch[y][x] == '|') {
                        isEnclosed = !isEnclosed
                    } else if (startingPipe != '.' && sketch[y][x] in "7J") {
                        if (startingPipe == 'F' && sketch[y][x] == 'J' ||
                            startingPipe == 'L' && sketch[y][x] == '7'
                        ) {
                            isEnclosed = !isEnclosed
                        }
                        startingPipe = '.'
                    } else if (startingPipe == '.' && sketch[y][x] in "FL") {
                        startingPipe = sketch[y][x]
                    }
                } else if (isEnclosed) {
                    enclosedTiles++
                }
            }
        }

        return enclosedTiles
    }

    private fun getDistances(sketch: Array<CharArray>): Array<IntArray> {
        val distances = Array(sketch.size) { IntArray(sketch[0].size) { -1 } }

        val positionQueue: Queue<Pair<Int, Int>> = LinkedList()
        val distanceQueue: Queue<Int> = LinkedList()
        val visited = mutableSetOf<Pair<Int, Int>>()

        positionQueue += findStart(sketch)
        distanceQueue += 0

        replaceStart(sketch)

        while (positionQueue.isNotEmpty()) {
            val (x, y) = positionQueue.remove()
            val distance = distanceQueue.remove()

            if (x to y in visited) continue
            visited += x to y

            distances[y][x] = distance

            if (y > 0 && sketch[y][x] in "|LJ" && sketch[y - 1][x] in "|7F") {
                positionQueue += x to y - 1
                distanceQueue += distance + 1
            }

            if (y < sketch.lastIndex && sketch[y][x] in "|7F" && sketch[y + 1][x] in "|LJ") {
                positionQueue += x to y + 1
                distanceQueue += distance + 1
            }

            if (x > 0 && sketch[y][x] in "-J7" && sketch[y][x - 1] in "-LF") {
                positionQueue += x - 1 to y
                distanceQueue += distance + 1
            }

            if (x < sketch[0].lastIndex && sketch[y][x] in "-LF" && sketch[y][x + 1] in "-J7") {
                positionQueue += x + 1 to y
                distanceQueue += distance + 1
            }
        }

        return distances
    }

    private fun replaceStart(sketch: Array<CharArray>) {
        val (x, y) = findStart(sketch)
        val north = y > 0 && sketch[y - 1][x] in "|7F"
        val south = y < sketch.lastIndex && sketch[y + 1][x] in "|LJ"
        val east = x < sketch[0].lastIndex && sketch[y][x + 1] in "-J7"
        val west = x > 0 && sketch[y][x - 1] in "-LF"
        sketch[y][x] = when {
            north && south -> '|'
            east && west -> '-'
            north && east -> 'L'
            north && west -> 'J'
            south && west -> '7'
            south && east -> 'F'
            else -> throw IllegalStateException()
        }
    }

    private fun findStart(sketch: Array<CharArray>): Pair<Int, Int> {
        val y = sketch.indexOfFirst { it.contains('S') }
        val x = sketch[y].indexOf('S')
        return x to y
    }

    private fun List<String>.toSketch(): Array<CharArray> {
        return map { it.toCharArray() }.toTypedArray()
    }
}