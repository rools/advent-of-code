package year2023

import kotlin.math.max
import kotlin.math.min

object Day1 {
    fun solve1(input: List<String>): Int {
        return input.sumOf { line ->
            10 * line.first { it.isDigit() }.digitToInt() +
                    line.last { it.isDigit() }.digitToInt()
        }
    }

    fun solve2(input: List<String>): Int {
        val digits = listOf(
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )

        return input.sumOf { line ->
            val first = digits.mapIndexed { index, str ->
                val digit = index + 1
                val i1 = line.indexOf(str).takeIf { it != -1 } ?: Int.MAX_VALUE
                val i2 = line.indexOf(digit.digitToChar()).takeIf { it != -1 } ?: Int.MAX_VALUE
                min(i1, i2) to digit
            }.minBy { it.first }.second

            val second = digits.mapIndexed { index, str ->
                val digit = index + 1
                val i1 = line.lastIndexOf(str)
                val i2 = line.lastIndexOf(digit.digitToChar())
                max(i1, i2) to digit
            }.maxBy { it.first }.second

            first * 10 + second
        }
    }
}