package year2022

object Day4 {
    fun solve1(input: List<String>): Int {
        return input.count { line ->
            val parts = line.split(Regex("\\D"))
                .map { it.toInt() }
            parts[0] >= parts[2] && parts[1] <= parts[3] || parts[2] >= parts[0] && parts[3] <= parts[1]
        }
    }

    fun solve2(input: List<String>): Int {
        return input.count { line ->
            val parts = line.split(Regex("\\D"))
                .map { it.toInt() }
            parts[0] <= parts[2] && parts[1] >= parts[2] || parts[0] <= parts[3] && parts[1] >= parts[3] ||
                    parts[2] <= parts[0] && parts[3] >= parts[0] || parts[2] <= parts[1] && parts[3] >= parts[1]
        }
    }
}