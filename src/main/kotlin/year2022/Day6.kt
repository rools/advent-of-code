package year2022

object Day6 {
    fun solve1(input: List<String>): Int {
        return input[0].findMarker(4)
    }

    fun solve2(input: List<String>): Int {
        return input[0].findMarker(14)
    }

    private fun String.findMarker(markerLength: Int): Int {
        return windowed(size = markerLength, step = 1)
            .indexOfFirst { it.toSet().size == markerLength } + markerLength
    }
}