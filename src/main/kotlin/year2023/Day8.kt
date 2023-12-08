package year2023

object Day8 {
    fun solve1(input: List<String>): Int {
        val (path, map) = parse(input)

        var location = "AAA"
        var steps = 0
        while (location != "ZZZ") {
            path.forEach { direction ->
                location = when (direction) {
                    'R' -> map.getValue(location).right
                    else -> map.getValue(location).left
                }
            }
            steps += path.length
        }

        return steps
    }

    fun solve2(input: List<String>): Long {
        val (path, map) = parse(input)

        val startLocations = map.keys.filter { it[2] == 'A' }

        val stepValues = startLocations.map { startLocation ->
            var location = startLocation
            var steps = 0
            while (location[2] != 'Z') {
                path.forEach { direction ->
                    location = when (direction) {
                        'R' -> map.getValue(location).right
                        else -> map.getValue(location).left
                    }
                }
                steps += path.length
            }
            steps
        }

        return stepValues.flatMap { factorize(it) }
            .distinct()
            .fold(1) { product, value -> product * value }
    }

    private fun factorize(value: Int): List<Int> = buildList {
        var remaining = value
        while (remaining > 1) {
            var factor = 2
            while (remaining % factor != 0) {
                factor++
            }
            add(factor)
            remaining /= factor
        }
    }

    private fun parse(input: List<String>): Pair<String, Map<String, Node>> {
        val path = input[0]
        val regex = Regex("(.{3}) = \\((.{3}), (.{3})\\)")
        val map: Map<String, Node> = input.drop(2).associate { line ->
            val groups = regex.matchEntire(line)!!.groupValues
            groups[1] to Node(
                left = groups[2],
                right = groups[3]
            )
        }
        return path to map
    }

    data class Node(
        val left: String,
        val right: String
    )
}