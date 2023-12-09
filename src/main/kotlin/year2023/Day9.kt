package year2023

object Day9 {
    fun solve1(input: List<String>): Int {
        return input.sumOf { line ->
            getDiffLists(line)
                .sumOf { it.last() }
        }
    }

    fun solve2(input: List<String>): Int {
        return input.sumOf { line ->
            getDiffLists(line)
                .fold(0.toInt()) { acc, diffs -> diffs[0] - acc }
        }
    }

    private fun getDiffLists(line: String): List<List<Int>> {
        val values = line.split(' ')
            .map { it.toInt() }

        val diffList = mutableListOf(values)

        do {
            val diffs = diffList.last().zipWithNext { a, b -> b - a }
            diffList += diffs
        } while (!diffList.last().all { it == 0 })

        return diffList.reversed()
    }
}