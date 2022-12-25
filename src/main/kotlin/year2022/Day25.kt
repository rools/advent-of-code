package year2022

object Day25 {
    fun solve1(input: List<String>): String {
        return input.sumOf { it.fromSnafu() }
            .toSnafu()
    }

    private fun String.fromSnafu(): Long {
        return fold(0) { acc, c ->
            acc * 5 + when (c) {
                '2' -> 2
                '1' -> 1
                '-' -> -1
                '=' -> -2
                else -> 0
            }
        }
    }

    private fun Long.toSnafu(): String {
        var snafu = ""
        var value = this

        while (value > 0) {
            val digit = value % 5

            snafu = when (digit) {
                0L -> '0'
                1L -> '1'
                2L -> '2'
                3L -> '='
                else -> '-'
            } + snafu

            value /= 5

            if (digit > 2) {
                value++
            }
        }

        return snafu
    }
}