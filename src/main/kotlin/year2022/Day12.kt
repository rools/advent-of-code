package year2022

import java.util.*

object Day12 {
    private val moves = listOf(
        -1 to 0,
        1 to 0,
        0 to -1,
        0 to 1
    )

    fun solve1(input: List<String>): Int {
        var curX = 0
        var curY = 0
        var goalX = 0
        var goalY = 0

        val grid = input.mapIndexed { y, row ->
            row.mapIndexed { x, height ->
                when (height) {
                    'S' -> {
                        curX = x
                        curY = y
                        'a'
                    }

                    'E' -> {
                        goalX = x
                        goalY = y
                        'z'
                    }

                    else -> height
                }
            }.toMutableList()
        }.toMutableList()

        val width = grid[0].size
        val height = grid.size

        val queue: Queue<Candidate> = LinkedList()

        queue += Candidate(curX, curY, 0)

        val visited = mutableSetOf<Pair<Int, Int>>()

        while (true) {
            val candidate = queue.remove()

            moves.forEach { (x, y) ->
                val newX = candidate.x + x
                val newY = candidate.y + y

                if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
                    return@forEach
                }

                if (grid[newY][newX] - grid[candidate.y][candidate.x] > 1) {
                    return@forEach
                }

                if (newX == goalX && newY == goalY) {
                    return candidate.steps + 1
                }

                if (newX to newY in visited) {
                    return@forEach
                }

                queue += Candidate(newX, newY, candidate.steps + 1)

                visited += newX to newY

            }
        }

        error("End not found")
    }

    fun solve2(input: List<String>): Any {
        var curX = 0
        var curY = 0

        val grid = input.mapIndexed { y, row ->
            row.mapIndexed { x, height ->
                when (height) {
                    'S' -> {
                        'a'
                    }

                    'E' -> {
                        curX = x
                        curY = y
                        'z'
                    }

                    else -> height
                }
            }.toMutableList()
        }.toMutableList()

        val width = grid[0].size
        val height = grid.size

        val queue: Queue<Candidate> = LinkedList()

        queue += Candidate(curX, curY, 0)

        val visited = mutableSetOf<Pair<Int, Int>>()

        while (true) {
            val candidate = queue.remove()

            moves.forEach { (x, y) ->
                val newX = candidate.x + x
                val newY = candidate.y + y

                if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
                    return@forEach
                }

                if (grid[candidate.y][candidate.x] - grid[newY][newX] > 1) {
                    return@forEach
                }

                if (grid[newY][newX] == 'a') {
                    return candidate.steps + 1
                }

                if (newX to newY in visited) {
                    return@forEach
                }

                queue += Candidate(newX, newY, candidate.steps + 1)

                visited += newX to newY

            }
        }

        error("End not found")
    }

    data class Candidate(
        val x: Int,
        val y: Int,
        val steps: Int
    )
}

