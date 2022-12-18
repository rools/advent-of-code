package year2022

import year2022.Day2.Shape.*

object Day2 {
    private const val LOSS_SCORE = 0
    private const val DRAW_SCORE = 3
    private const val WIN_SCORE = 6

    private enum class Shape(val score: Int) {
        ROCK(1),
        PAPER(2),
        SCISSORS(3)
    }

    fun solve1(input: List<String>): Int {
        return input.sumOf { line ->
            val parts = line.split(' ')
            val theirShape = parts[0].toShape()
            val myShape = parts[1].toShape()

            when (theirShape) {
                myShape.defeats() -> WIN_SCORE
                myShape.defeatedBy() -> LOSS_SCORE
                else -> DRAW_SCORE
            } + myShape.score
        }
    }

    fun solve2(input: List<String>): Int {
        return input.sumOf { line ->
            val parts = line.split(' ')
            val theirShape = parts[0].toShape()

            when (val outcome = parts[1]) {
                "X" -> LOSS_SCORE + theirShape.defeats().score
                "Y" -> DRAW_SCORE + theirShape.score
                "Z" -> WIN_SCORE + theirShape.defeatedBy().score
                else -> error("Unknown outcome $outcome")
            }
        }
    }

    private fun String.toShape() = when (this) {
        "A", "X" -> ROCK
        "B", "Y" -> PAPER
        "C", "Z" -> SCISSORS
        else -> error("Unknown shape $this")
    }

    private fun Shape.defeats() = when (this) {
        ROCK -> SCISSORS
        PAPER -> ROCK
        SCISSORS -> PAPER
    }

    private fun Shape.defeatedBy() = when (this) {
        ROCK -> PAPER
        PAPER -> SCISSORS
        SCISSORS -> ROCK
    }
}