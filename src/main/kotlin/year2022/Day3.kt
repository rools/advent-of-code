package year2022

object Day3 {
    fun solve1(input: List<String>): Int {
        return input.sumOf { line ->
            val compartments = line.chunked(line.length / 2)
            priority(compartments.firstCommonChar())
        }
    }

    fun solve2(input: List<String>): Int {
        return input.chunked(3)
            .sumOf { priority(it.firstCommonChar()) }
    }

    private fun List<String>.firstCommonChar(): Char {
        return map { it.toSet() }
            .reduce { common, string -> common intersect string }
            .first()
    }

    private fun priority(item: Char): Int = when {
        item >= 'a' -> item - 'a' + 1
        else -> item - 'A' + 27
    }
}