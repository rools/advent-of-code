package year2022

import java.util.*

object Day9 {
    fun solve1(input: List<String>): Int {
        val head = Pos(0, 0)
        val tail = Pos(0, 0)

        val visited = mutableSetOf<Pos>()

        visited += tail.copy()

        input.forEach { line ->
            val parts = line.split(' ')
            val dir = parts[0]
            val steps = parts[1].toInt()

            repeat(steps) {
                step(dir, head, tail)
                visited += tail.copy()
            }

        }

        return visited.size
    }

    fun solve2(input: List<String>): Int {
        val rope = LinkedList<Pos>()
        repeat(10) { rope.add(Pos(0, 0)) }

        val visited = mutableSetOf<Pos>()

        visited += rope.last.copy()

        input.forEach { line ->
            val parts = line.split(' ')
            val dir = parts[0]
            val steps = parts[1].toInt()

            repeat(steps) {
                val head = rope.first
                when (dir) {
                    "R" -> head.x++
                    "L" -> head.x--
                    "U" -> head.y++
                    "D" -> head.y--
                }

                1.until(rope.size).forEach { i ->
                    update(rope[i - 1], rope[i])
                }

                visited += rope.last.copy()
            }

        }

        return visited.size
    }

    private fun step(dir: String, head: Pos, tail: Pos) {
        when (dir) {
            "R" -> {
                head.x++
                if (tail.x < head.x - 1) {
                    tail.x = head.x - 1
                    tail.y = head.y
                }
            }

            "L" -> {
                head.x--
                if (tail.x > head.x + 1) {
                    tail.x = head.x + 1
                    tail.y = head.y
                }
            }

            "U" -> {
                head.y++
                if (tail.y < head.y - 1) {
                    tail.y = head.y - 1
                    tail.x = head.x
                }
            }

            "D" -> {
                head.y--
                if (tail.y > head.y + 1) {
                    tail.y = head.y + 1
                    tail.x = head.x
                }
            }
        }
    }

    private fun update(head: Pos, tail: Pos) {
        if (tail.x < head.x - 1) {
            tail.x++

            if (tail.y > head.y) {
                tail.y--
            } else if (tail.y < head.y) {
                tail.y++
            }

        } else if (tail.x > head.x + 1) {
            tail.x--

            if (tail.y > head.y) {
                tail.y--
            } else if (tail.y < head.y) {
                tail.y++
            }
        }

        if (tail.y < head.y - 1) {
            tail.y++
            tail.x = head.x

            if (tail.x > head.x) {
                tail.x--
            } else if (tail.x < head.x) {
                tail.x++
            }
        } else if (tail.y > head.y + 1) {
            tail.y--
            tail.x = head.x

            if (tail.x > head.x) {
                tail.x--
            } else if (tail.x < head.x) {
                tail.x++
            }
        }
    }

    private data class Pos(var x: Int, var y: Int)
}

