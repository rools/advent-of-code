package year2023

import lib.algos.split

object Day13 {
    fun solve1(input: List<String>): Int {
        return input.split("").sumOf { pattern ->
            val pattern = pattern.map { it.toCharArray() }.toTypedArray()
            100 * getRowMatches(pattern) + getRowMatches(pattern.transposed())
        }
    }

    fun solve2(input: List<String>): Int {
        return input.split("").sumOf { pattern ->
            val pattern = pattern.map { it.toCharArray() }.toTypedArray()
            findNewReflection(pattern)
        }
    }

    private fun findNewReflection(pattern: Array<CharArray>): Int {
        val originalRow = getRowMatches(pattern)
        val originalColumn = getRowMatches(pattern.transposed())

        pattern.indices.forEach { y ->
            pattern[0].indices.forEach { x ->
                val original = pattern[y][x]
                pattern[y][x] = if (original == '#') '.' else '#'

                val newRow = getRowMatches(pattern, ignoreRow = originalRow)
                val newColumn = getRowMatches(pattern.transposed(), ignoreRow = originalColumn)

                if (newRow > 0 && newRow != originalRow) {
                    return 100 * newRow
                }

                if (newColumn > 0 && newColumn != originalColumn) {
                    return newColumn
                }

                pattern[y][x] = original
            }
        }

        return 0
    }

    private fun getRowMatches(pattern: Array<CharArray>, ignoreRow: Int = 0): Int {
        for (row in 1..pattern.lastIndex) {
            if (row == ignoreRow) continue
            var y = 0
            var isMirrored = true
            while (row >= y + 1 && row + y < pattern.size) {
                val row1 = (row - y) - 1
                val row2 = row + y

                if (!(0..pattern[0].lastIndex).all { column -> pattern[row1][column] == pattern[row2][column] }) {
                    isMirrored = false
                    break
                }

                y++
            }
            if (isMirrored) return row
        }
        return 0
    }

    private fun Array<CharArray>.transposed(): Array<CharArray> {
        return Array(get(0).size) { row ->
            CharArray(size) { column -> get(column)[row] }
        }
    }
}