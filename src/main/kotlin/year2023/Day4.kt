package year2023

object Day4 {
    fun solve1(input: List<String>): Int {
        return getMatchCounts(input)
            .sumOf { if (it == 0) 0 else 1 shl (it - 1) }
    }

    fun solve2(input: List<String>): Int {
        val matchCounts = getMatchCounts(input)
        return matchCounts
            .indices
            .sumOf { getCount(it + 1, matchCounts) }
    }

    private fun getCount(card: Int, matchCount: List<Int>): Int {
        return 1 + (card + 1..card + matchCount[card - 1])
            .sumOf { if (it > matchCount.size) 0 else getCount(it, matchCount) }
    }

    private fun getMatchCounts(input: List<String>): List<Int> {
        return input.map { line ->
            val parts = line.substringAfter(": ")
                .split(" | ")

            val winningNumbers = parts[0]
                .chunked(3)
                .map { it.trim().toInt() }
                .toSet()

            val numbers = parts[1]
                .chunked(3)
                .map { it.trim().toInt() }
                .toSet()

            winningNumbers.intersect(numbers).count()
        }
    }
}