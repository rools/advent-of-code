package year2023

import year2023.Day14.Direction.*

object Day14 {
    fun solve1(input: List<String>): Int {
        val platform = input.toPlatform()
        cycle(platform, NORTH)
        return calculateLoad(platform)
    }

    fun solve2(input: List<String>): Int {
        val platform = input.toPlatform()

        var cycles = 0
        val hashToCycle = mutableMapOf<Long, Int>()
        val loads = mutableListOf<Int>()

        while (true) {
            cycle(platform, NORTH)
            cycle(platform, WEST)
            cycle(platform, SOUTH)
            cycle(platform, EAST)

            val hash = calculateHash(platform)

            if (hash in hashToCycle) {
                val start = hashToCycle.getValue(hash)
                val length = cycles - start
                val index = (1_000_000_000 - start) % length + start - 1
                return loads[index]
            }

            hashToCycle[hash] = cycles++
            loads += calculateLoad(platform)
        }
    }

    private enum class Direction { NORTH, SOUTH, WEST, EAST }

    private fun cycle(platform: Array<CharArray>, direction: Direction) {
        when (direction) {
            NORTH -> platform.indices.forEach { y ->
                repeat(platform[0].size) { x -> move(platform, x, y, direction) }
            }

            SOUTH -> platform.indices.reversed().forEach { y ->
                repeat(platform[0].size) { x -> move(platform, x, y, direction) }
            }

            WEST -> platform[0].indices.forEach { x ->
                repeat(platform.size) { y -> move(platform, x, y, direction) }
            }

            EAST -> platform[0].indices.reversed().forEach { x ->
                repeat(platform.size) { y -> move(platform, x, y, direction) }
            }
        }
    }

    private fun move(platform: Array<CharArray>, x: Int, y: Int, direction: Direction) {
        if (platform[y][x] != 'O') return
        platform[y][x] = '.'

        val dy = if (direction == NORTH) -1 else if (direction == SOUTH) 1 else 0
        val dx = if (direction == WEST) -1 else if (direction == EAST) 1 else 0

        var newY = y
        var newX = x

        while (newY in platform.indices && newX in platform[0].indices && platform[newY][newX] == '.') {
            newY += dy
            newX += dx
        }

        platform[newY - dy][newX - dx] = 'O'
    }

    private fun calculateLoad(platform: Array<CharArray>): Int {
        return platform.indices.sumOf { row ->
            val factor = platform.size - row
            factor * platform[row].count { it == 'O' }
        }
    }

    private fun calculateHash(platform: Array<CharArray>): Long {
        var hash = 0L
        platform.forEach { row ->
            row.forEach { hash = 31 * hash + it.code }
        }
        return hash
    }

    private fun List<String>.toPlatform(): Array<CharArray> {
        return map { it.toCharArray() }.toTypedArray()
    }
}