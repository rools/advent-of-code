package year2022

import kotlin.math.max

object Day8 {
    fun solve1(input: List<String>): Int {
        val grid = input.map { it.windowed(1, 1).map { it.toInt() } }
        val width = grid[0].size
        val height = grid.size

        var count = 0

        repeat(width) { x ->
            repeat(height) { y ->
                if (isVisible(grid, x, y)) {
                    count++
                }
            }
        }

        return count
    }

    fun solve2(input: List<String>): Int {
        val grid = input.map { it.windowed(1, 1).map { it.toInt() } }
        val width = grid[0].size
        val height = grid.size

        var maxScore = 0

        repeat(width) { x ->
            repeat(height) { y ->
                maxScore = max(maxScore, scenicScore(grid, x, y))
            }
        }

        return maxScore
    }

    private fun isVisible(grid: List<List<Int>>, x: Int, y: Int): Boolean {
        val width = grid[0].size
        val height = grid.size

        val value = grid[x][y]

        var curX = 0
        while (true) {
            if (x == curX) return true
            if (grid[curX][y] >= value) break
            curX++
        }

        curX = width - 1
        while (true) {
            if (x == curX) return true
            if (grid[curX][y] >= value) break
            curX--
        }

        var curY = 0
        while (true) {
            if (y == curY) return true
            if (grid[x][curY] >= value) break
            curY++
        }

        curY = height - 1
        while (true) {
            if (y == curY) return true
            if (grid[x][curY] >= value) break
            curY--
        }

        return false
    }

    private fun scenicScore(grid: List<List<Int>>, x: Int, y: Int): Int {
        val width = grid[0].size
        val height = grid.size

        val value = grid[x][y]

        var score = 1

        var curX = x
        while (true) {
            if (curX == 0) break
            curX--
            if (grid[curX][y] >= value) break
        }
        score *= x - curX

        curX = x
        while (true) {
            if (curX == width - 1) break
            curX++
            if (grid[curX][y] >= value) break
        }
        score *= curX - x

        var curY = y
        while (true) {
            if (curY == 0) break
            curY--
            if (grid[x][curY] >= value) break
        }
        score *= y - curY

        curY = y
        while (true) {
            if (curY == height - 1) break
            curY++
            if (grid[x][curY] >= value) break
        }
        score *= curY - y

        return score
    }
}