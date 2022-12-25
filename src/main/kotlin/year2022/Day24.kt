package year2022

import lib.data.*
import java.util.*

object Day24 {
    fun solve1(input: List<String>): Int {
        val valley = parseValley(input)
        return search(
            start = Step(pos = valley.entrance, steps = 0),
            goal = valley.exit,
            valley = valley
        )
    }

    fun solve2(input: List<String>): Int {
        val valley = parseValley(input)

        val firstTripSteps = search(
            start = Step(pos = valley.entrance, steps = 1),
            goal = valley.exit,
            valley = valley
        )

        val secondTripSteps = search(
            start = Step(pos = valley.exit, steps = firstTripSteps + 1),
            goal = valley.entrance,
            valley = valley
        )

        return search(
            start = Step(pos = valley.entrance, steps = secondTripSteps),
            goal = valley.exit,
            valley = valley
        )
    }

    private fun search(
        start: Step,
        goal: Vec2,
        valley: Valley
    ): Int {
        val moves = listOf(
            Vec2(0, 0),
            Vec2(0, -1),
            Vec2(1, 0),
            Vec2(0, 1),
            Vec2(-1, 0)
        )

        val queue = PriorityQueue<Step> { p0, p1 ->
            p0.pos.distanceTo(goal) - p1.pos.distanceTo(goal)
        }

        val visited = mutableSetOf<Step>()

        val blizzardsCache = mutableListOf<List<Pair<Vec2, Char>>>()
        blizzardsCache += valley.initialBlizzards
        repeat(start.steps) { step ->
            blizzardsCache += stepBlizzards(blizzardsCache[step], valley.rect)
        }

        queue += start

        var minSteps = Int.MAX_VALUE

        while (queue.isNotEmpty()) {
            val step = queue.remove()

            if (step.steps + step.pos.distanceTo(goal) >= minSteps) continue

            if (step.pos == goal) {
                minSteps = step.steps
                continue
            }

            if (blizzardsCache.lastIndex < step.steps) {
                blizzardsCache += stepBlizzards(blizzardsCache[step.steps - 1], valley.rect)
            }
            val blizzards = blizzardsCache[step.steps]

            moves.forEach { move ->
                val newPos = step.pos + move
                if (newPos != start.pos && newPos != goal && newPos !in valley.rect) return@forEach
                if (blizzards.any { it.first == newPos }) return@forEach
                val newStep = Step(
                    pos = newPos,
                    steps = step.steps + 1
                )
                if (newStep in visited) return@forEach
                visited += newStep
                queue += newStep
            }
        }

        return minSteps - 1
    }

    private fun stepBlizzards(
        blizzards: List<Pair<Vec2, Char>>,
        rect: Rect
    ): List<Pair<Vec2, Char>> {
        return blizzards.map { (pos, direction) ->
            val newPos = when (direction) {
                '^' -> pos.copy(y = (pos.y - 1 + rect.height) % rect.height)
                '>' -> pos.copy(x = (pos.x + 1) % rect.width)
                'v' -> pos.copy(y = (pos.y + 1) % rect.height)
                else -> pos.copy(x = (pos.x - 1 + rect.width) % rect.width)
            }
            newPos to direction
        }
    }

    private fun parseValley(input: List<String>): Valley {
        return Valley(
            initialBlizzards = buildList {
                input.forEachIndexed { y, row ->
                    row.forEachIndexed { x, c ->
                        if (c == '.' || c == '#') return@forEachIndexed
                        add(Vec2(x - 1, y - 1) to c)
                    }
                }
            },
            rect = Rect(0, 0, input[0].length - 3, input.size - 3),
            entrance = Vec2(0, -1),
            exit = Vec2(input[0].length - 3, input.size - 2)
        )
    }

    data class Step(
        val pos: Vec2,
        val steps: Int
    )

    private data class Valley(
        val initialBlizzards: List<Pair<Vec2, Char>>,
        val rect: Rect,
        val entrance: Vec2,
        val exit: Vec2
    )
}