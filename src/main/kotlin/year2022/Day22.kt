package year2022

import kotlin.math.sqrt

object Day22 {
    private const val DIRECTION_RIGHT = 0
    private const val DIRECTION_DOWN = 1
    private const val DIRECTION_LEFT = 2
    private const val DIRECTION_UP = 3

    fun solve1(input: List<String>): Int {
        val map = parseMap(input)
        val path = parsePath(input.last())

        var x = map[0].indexOf('.')
        var y = 0

        var direction = DIRECTION_RIGHT

        path.forEach { instruction ->
            when (instruction) {
                is Int -> {
                    val stepX = when (direction) {
                        DIRECTION_RIGHT -> 1
                        DIRECTION_LEFT -> -1
                        else -> 0
                    }
                    val stepY = when (direction) {
                        DIRECTION_DOWN -> 1
                        DIRECTION_UP -> -1
                        else -> 0
                    }
                    repeat(instruction) {
                        var nextX = x + stepX
                        var nextY = y + stepY

                        if (!isInMap(nextX, nextY, map)) {
                            when (direction) {
                                DIRECTION_RIGHT -> nextX = map[nextY].indexOfFirst { it != ' ' }
                                DIRECTION_DOWN -> nextY = map.indexOfFirst { it.size > nextX && it[nextX] != ' ' }
                                DIRECTION_LEFT -> nextX = map[nextY].indexOfLast { it != ' ' }
                                DIRECTION_UP -> nextY = map.indexOfLast { it.size > nextX && it[nextX] != ' ' }
                            }
                        }

                        if (map[nextY][nextX] != '#') {
                            x = nextX
                            y = nextY
                        }
                    }
                }

                is Char -> {
                    direction = (direction + if (instruction == 'R') 1 else 3) % 4
                }
            }
        }

        return 1000 * (y + 1) + 4 * (x + 1) + direction
    }

    fun solve2(input: List<String>): Int {
        val map = parseMap(input)
        val path = parsePath(input.last())

        val faceSize = sqrt(map.sumOf { row -> row.count { it != ' ' } } / 6f).toInt()

        var x = map[0].indexOf('.')
        var y = 0

        var direction = DIRECTION_RIGHT

        path.forEach { instruction ->
            when (instruction) {
                is Int -> {
                    var steps = 0

                    while (steps < instruction) {
                        val stepX = when (direction) {
                            DIRECTION_RIGHT -> 1
                            DIRECTION_LEFT -> -1
                            else -> 0
                        }
                        val stepY = when (direction) {
                            DIRECTION_DOWN -> 1
                            DIRECTION_UP -> -1
                            else -> 0
                        }

                        var nextX = x + stepX
                        var nextY = y + stepY
                        var newOrientation = direction

                        if (!isInMap(nextX, nextY, map)) {
                            val res = wrap(map, faceSize, x, y, direction)
                            nextX = res.first
                            nextY = res.second
                            newOrientation = res.third
                        }

                        if (map[nextY][nextX] == '#') break

                        direction = newOrientation
                        x = nextX
                        y = nextY

                        steps++
                    }
                }

                is Char -> {
                    direction = (direction + if (instruction == 'R') 1 else 3) % 4
                }
            }
        }

        return 1000 * (y + 1) + 4 * (x + 1) + direction
    }

    private fun wrap(
        map: Array<CharArray>,
        faceSize: Int,
        x: Int,
        y: Int,
        dir: Int
    ): Triple<Int, Int, Int> {

        data class CandidateFace(
            val x: Int,
            val y: Int,
            val direction: Int
        )

        // Candidate faces we might move into, order matters
        var candidateFaces = listOf(
            CandidateFace(1, 0, DIRECTION_DOWN),
            CandidateFace(1, 0, DIRECTION_UP),
            CandidateFace(2, -2, DIRECTION_LEFT),
            CandidateFace(-4, -2, DIRECTION_RIGHT),
            CandidateFace(-1, 4, DIRECTION_UP),
            CandidateFace(-3, -2, DIRECTION_DOWN),
            CandidateFace(0, 2, DIRECTION_LEFT)
        )

        // Rotate candidates according to our current direction
        repeat(dir) {
            candidateFaces = candidateFaces.map { candidate ->
                CandidateFace(
                    x = -candidate.y,
                    y = candidate.x,
                    direction = (candidate.direction + 1) % 4
                )
            }
        }

        val faceX = x / faceSize
        val faceY = y / faceSize

        candidateFaces.forEach { candidate ->
            val candidateFaceX = faceX + candidate.x
            val candidateFaceY = faceY + candidate.y

            val relativeRotation = (candidate.direction - dir + 4) % 4

            var xInFace = x % faceSize
            var yInFace = y % faceSize
            repeat(relativeRotation) {
                val oldYInFace = yInFace
                yInFace = xInFace
                xInFace = faceSize - oldYInFace - 1
            }

            var candidateX = candidateFaceX * faceSize + xInFace
            var candidateY = candidateFaceY * faceSize + yInFace

            when (candidate.direction) {
                DIRECTION_RIGHT -> candidateX++
                DIRECTION_DOWN -> candidateY++
                DIRECTION_LEFT -> candidateX--
                DIRECTION_UP -> candidateY--
            }

            if (!isInMap(candidateX, candidateY, map)) return@forEach

            return Triple(candidateX, candidateY, candidate.direction)
        }

        error("No face found")
    }

    private fun parseMap(input: List<String>): Array<CharArray> {
        return input.dropLast(2)
            .map { it.toCharArray() }
            .toTypedArray()
    }

    private fun parsePath(input: String): List<Any> {
        val path = mutableListOf<Any>()

        var steps = 0

        input.forEach { c ->
            if (c.isDigit()) {
                steps = steps * 10 + c.digitToInt()
            } else {
                path += steps
                path += c
                steps = 0
            }
        }

        path += steps

        return path
    }

    private fun isInMap(x: Int, y: Int, map: Array<CharArray>): Boolean {
        return y >= 0 && y < map.size &&
                x >= 0 && x < map[y].size &&
                map[y][x] != ' '
    }
}