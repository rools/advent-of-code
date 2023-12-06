package year2023

object Day6 {
    fun solve1(input: List<String>): Int {
        val races = parseRaces(input)
        return races.fold(1) { product, race ->
            product * recordCount(race)
        }
    }

    fun solve2(input: List<String>): Int {
        val race = parseRaces(input.map { it.replace(" ", "") })[0]
        return recordCount(race)
    }

    private fun recordCount(race: Race): Int {
        return (1..race.time).count { time ->
            val distance = time * (race.time - time)
            distance > race.distance
        }
    }

    private fun parseRaces(input: List<String>): List<Race> {
        val lists = input.map { list ->
            list.substringAfter(':')
                .trim()
                .split(Regex("\\s+"))
                .map { it.toLong() }
        }
        return lists[0].zip(lists[1]) { time, distance -> Race(time, distance) }
    }

    data class Race(
        val time: Long,
        val distance: Long
    )
}