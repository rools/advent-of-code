package year2023

import lib.data.Direction
import lib.data.Vec2
import lib.data.plus
import lib.utils.toMatrix
import java.util.*

object Day23 {
    fun solve1(input: List<String>): Int {
        val map = input.toMatrix()

        val posQueue: Queue<Vec2> = LinkedList()
        val visitedQueue: Queue<Set<Vec2>> = LinkedList()

        posQueue += Vec2(1, 0)
        visitedQueue.add(emptySet())

        val goal = Vec2(map.cols - 2, map.rows - 1)

        var maxSteps = 0

        while (posQueue.isNotEmpty()) {
            val position = posQueue.remove()
            val visited = visitedQueue.remove()

            if (position in visited) continue

            if (position == goal) {
                if (visited.size > maxSteps) maxSteps = visited.size
                continue
            }

            Direction.values().forEach { direction ->
                var nextPos = position + direction

                if (nextPos !in map || nextPos in visited || map[nextPos] == '#') {
                    return@forEach
                }

                when (map[nextPos]) {
                    '>' -> {
                        visitedQueue.add(visited + position + nextPos)
                        nextPos += Direction.EAST
                    }

                    'v' -> {
                        visitedQueue.add(visited + position + nextPos)
                        nextPos += Direction.SOUTH
                    }

                    else -> {
                        visitedQueue.add(visited + position)
                    }
                }

                posQueue += nextPos
            }
        }

        return maxSteps
    }

    fun solve2(input: List<String>): Int {
        val map = input.toMatrix()

        val goal = Vec2(map.cols - 2, map.rows - 1)

        val stack = Stack<Step>()
        val visited = mutableSetOf<Vec2>()

        visited += Vec2(1, 0)

        stack += Step(
            position = Vec2(1, 0),
            directionIndex = 0
        )

        var maxSteps = 0

        while (stack.isNotEmpty()) {
            val step = stack.pop()

            if (step.position == goal) {
                visited -= step.position
                if (visited.size > maxSteps) {
                    maxSteps = visited.size
                }
                continue
            }

            var nextIndex = step.directionIndex

            while (true) {
                if (nextIndex > Direction.values().lastIndex) {
                    visited -= step.position
                    break
                }

                val nextPos = step.position + Direction.values()[nextIndex]
                if (nextPos !in map || nextPos in visited || map[nextPos] == '#') {
                    nextIndex++
                } else {
                    stack += Step(position = step.position, directionIndex = nextIndex + 1)
                    stack += Step(position = nextPos, directionIndex = 0)
                    visited += nextPos
                    break
                }
            }
        }

        return maxSteps
    }

    private data class Step(
        val position: Vec2,
        val directionIndex: Int
    )
}