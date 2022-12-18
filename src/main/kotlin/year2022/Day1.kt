package year2022

import lib.algos.split

object Day1 {
    fun solve1(input: List<String>): Int {
        return parseCalories(input).max()
    }

    fun solve2(input: List<String>): Int {
        return parseCalories(input)
            .sortedDescending()
            .take(3)
            .sum()
    }

    private fun parseCalories(input: List<String>): List<Int> {
        return input.split("")
            .map { calories -> calories.sumOf { it.toInt() } }
    }
}