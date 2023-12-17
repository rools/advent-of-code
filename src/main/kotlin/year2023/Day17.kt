package year2023

import lib.data.Direction
import lib.data.Vec2
import lib.data.opposite
import lib.data.plus
import java.util.*
import kotlin.math.min

object Day17 {
    fun solve1(input: List<String>): Int {
        return minHeatLoss(input, minDirection = 0, maxDirection = 3)
    }

    fun solve2(input: List<String>): Int {
        return minHeatLoss(input, minDirection = 4, maxDirection = 10)
    }

    private fun minHeatLoss(map: List<String>, minDirection: Int, maxDirection: Int): Int {
        val states: Queue<Pair<State, Int>> = PriorityQueue { (_, hl1), (_, hl2) -> hl1.compareTo(hl2) }

        states += State(
            pos = Vec2(0, 0),
            lastDirection = null,
            directionCount = 0
        ) to 0

        val minHeatLosses: MutableMap<State, Int> = mutableMapOf()
        var min = Int.MAX_VALUE

        while (states.isNotEmpty()) {
            val (state, heatLoss) = states.remove()

            val minHeatLoss = minHeatLosses[state] ?: Int.MAX_VALUE
            if (minHeatLoss <= heatLoss) {
                continue
            }
            minHeatLosses[state] = heatLoss

            if (state.pos.y == map.lastIndex && state.pos.x == map[0].lastIndex) {
                min = min(min, heatLoss)
            }

            Direction.values().forEach { direction ->
                if (state.lastDirection != null &&
                    state.lastDirection != direction &&
                    state.directionCount < minDirection
                ) return@forEach

                if (state.lastDirection == direction.opposite ||
                    state.lastDirection == direction && state.directionCount >= maxDirection
                ) return@forEach

                val nextPos = state.pos + direction
                if (nextPos.y !in map.indices || nextPos.x !in map[0].indices) {
                    return@forEach
                }

                states += State(
                    pos = nextPos,
                    lastDirection = direction,
                    directionCount = if (state.lastDirection == direction) state.directionCount + 1 else 1
                ) to heatLoss + map[nextPos.y][nextPos.x].digitToInt()
            }
        }

        return min
    }

    private data class State(
        val pos: Vec2,
        val lastDirection: Direction?,
        val directionCount: Int
    )
}