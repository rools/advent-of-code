package year2023

import lib.data.Direction
import lib.data.Direction.*
import lib.data.Vec2
import lib.data.plus
import kotlin.math.max

object Day16 {
    fun solve1(input: List<String>): Int {
        return getEnergizedPositions(input, Vec2(0, 0), EAST)
    }

    fun solve2(input: List<String>): Int {
        var max = 0

        input.indices.forEach { y ->
            max = max(max, getEnergizedPositions(input, Vec2(0, y), EAST))
            max = max(max, getEnergizedPositions(input, Vec2(input[0].lastIndex, y), WEST))
        }

        input[0].indices.forEach { x ->
            max = max(max, getEnergizedPositions(input, Vec2(x, 0), SOUTH))
            max = max(max, getEnergizedPositions(input, Vec2(x, input.lastIndex), NORTH))
        }

        return max
    }

    private fun getEnergizedPositions(layout: List<String>, start: Vec2, direction: Direction): Int {
        val energizedPositions = mutableMapOf<Vec2, MutableSet<Direction>>()
        step(layout, start, direction, energizedPositions)
        return energizedPositions.size
    }

    private fun step(
        layout: List<String>,
        pos: Vec2,
        direction: Direction,
        energizedPositions: MutableMap<Vec2, MutableSet<Direction>>
    ) {
        if (pos.y !in layout.indices || pos.x !in layout[0].indices) return

        val directions = energizedPositions.getOrPut(pos) { mutableSetOf() }
        if (direction in directions) return
        directions += direction

        val tile = layout[pos.y][pos.x]

        if (tile == '|' && (direction == EAST || direction == WEST)) {
            step(layout, pos, NORTH, energizedPositions)
            step(layout, pos, SOUTH, energizedPositions)
            return
        }

        if (tile == '-' && (direction == NORTH || direction == SOUTH)) {
            step(layout, pos, EAST, energizedPositions)
            step(layout, pos, WEST, energizedPositions)
            return
        }

        val nextDirection = when (tile) {
            '\\' -> when (direction) {
                NORTH -> WEST
                EAST -> SOUTH
                SOUTH -> EAST
                WEST -> NORTH
            }

            '/' -> when (direction) {
                NORTH -> EAST
                EAST -> NORTH
                SOUTH -> WEST
                WEST -> SOUTH
            }

            else -> direction
        }

        step(layout, pos + nextDirection, nextDirection, energizedPositions)
    }
}